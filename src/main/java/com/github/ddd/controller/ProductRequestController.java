package com.github.ddd.controller;

import com.github.analysis.ThreadContextHolder;
import com.github.analysis.TransactionID;
import com.github.annotation.Compensable;
import com.github.annotation.Transaction;
import com.github.common.cons.TransactionIDEnum;
import com.github.ddd.businessObject.ProductRequest;
import com.github.jpa.lock.IObjectLockMethod;
import com.github.jpa.lock.ObjectIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Transaction
@RestController
@RequestMapping("/productRequest")
@Compensable(interfaceClass = IProductRequestController.class, confirmableKey = "ProductDataGenerator", cancellableKey = "ProductDataGeneratorCancel")
public class ProductRequestController implements IProductRequestController {


    @Autowired
    private IObjectLockMethod objectMethod;


    @ResponseBody
    @RequestMapping(value = "/objectLock/req", method = RequestMethod.GET)
    @TransactionID(value = TransactionIDEnum.JCW02)
    @Override
    public void initData() {
        final String transactionID = TransactionIDEnum.JCW02.getValue();
        ThreadContextHolder.setTransactionId(transactionID);
        objectMethod.objectLock(ProductRequest.class,new ObjectIdentifier("","0"));
    }
}
