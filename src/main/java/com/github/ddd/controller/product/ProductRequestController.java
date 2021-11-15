package com.github.ddd.controller.product;

import com.github.analysis.AnalysisPostProcessProxy;
import com.github.analysis.EnablePostProcess;
import com.github.analysis.ThreadContextHolder;
import com.github.analysis.TransactionID;
import com.github.annotation.Compensable;
import com.github.annotation.Transaction;
import com.github.common.cons.TransactionIDEnum;
import com.github.ddd.jpa.CoreJpaRepository;
import com.github.ddd.businessObject.ProductRequest;
import com.github.ddd.domainObject.ProductRequestDO;
import com.github.jpa.lock.IObjectLockMethod;
import com.github.jpa.lock.ObjectIdentifier;
import javax.persistence.Query;
import com.github.mvc.repository.ProductRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Transaction
@RestController
@RequestMapping("/productRequest")
@Compensable(interfaceClass = IProductRequestController.class, confirmableKey = "ProductDataGenerator", cancellableKey = "ProductDataGeneratorCancel")
public class ProductRequestController implements IProductRequestController {


    @Autowired
    private IObjectLockMethod objectMethod;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CoreJpaRepository coreJpaRepository;


    /**
     * 没有解决并发问题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/objectLock/req", method = RequestMethod.GET)
    @TransactionID(value = TransactionIDEnum.JCW02)
    @Override
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    public List<ProductRequestDO> initData() {
        final String transactionID = TransactionIDEnum.JCW02.getValue();
        ThreadContextHolder.setTransactionId(transactionID);
        objectMethod.objectLock(ProductRequest.class, new ObjectIdentifier("", "0"));
        Query query = entityManager.createQuery("from ProductRequestDO where id = :id");
        query.setParameter("id", "0");
//        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        List<ProductRequestDO> list = query.getResultList();
        if (!CollectionUtils.isEmpty(list)) {
            ProductRequestDO productRequestDO = list.get(0);
            log.info("/objectLock/req productRequestDO:{}",productRequestDO);
            productRequestDO.setRequestNumber(productRequestDO.getRequestNumber() + 1);
            coreJpaRepository.update(productRequestDO);
        }
        return query.getResultList();
    }

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @ResponseBody
    @RequestMapping(value = "/update/req", method = RequestMethod.GET)
    @EnablePostProcess(proxy = AnalysisPostProcessProxy.class)
    public synchronized String update() {
        ProductRequestDO productRequestDO = productRequestRepository.findById("0").get();
        productRequestDO.setRequestNumber(productRequestDO.getRequestNumber() + 1);
        productRequestRepository.save(productRequestDO);
        return "{\"info\":\"test EnablePostProcess\"}";
    }
}
