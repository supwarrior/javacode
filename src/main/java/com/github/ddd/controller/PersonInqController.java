package com.github.ddd.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.analysis.ThreadContextHolder;
import com.github.analysis.TransactionID;
import com.github.annotation.Compensable;
import com.github.annotation.Transaction;
import com.github.common.cons.TransactionIDEnum;
import com.github.ddd.businessObject.BaseBO;
import com.github.ddd.businessObject.Person;
import com.github.ddd.factory.BaseCoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这里模仿了DDD设计领域及TCC及Transaction
 *
 * @author 康盼Java开发工程师
 */
@Transaction
@RestController
@RequestMapping("/person")
@Compensable(interfaceClass = IPersonController.class, confirmableKey = "PersonDataGenerator", cancellableKey = "PersonDataGeneratorCancel")
public class PersonInqController implements IPersonController {

    @Autowired
    private BaseCoreFactory baseCoreFactory;


    @GetMapping(path = "/person/inq/{id}")
    @TransactionID(value = TransactionIDEnum.JCW01)
    public Object getPersonById(@PathVariable(value = "id") String id) {
        final String transactionID = TransactionIDEnum.JCW01.getValue();
        ThreadContextHolder.setTransactionId(transactionID);
        Person person = baseCoreFactory.getBO(Person.class, id);
        person.business();
        return person.getEntity();
    }

    @GetMapping(path = "/person_list/inq/{identifier}")
    public String getPersonByNamedIdentifier(@PathVariable(value = "identifier") String identifier) {
        List<BaseBO> list = baseCoreFactory.getBOByNamedIdentifier(Person.class, identifier);
        return JSONObject.toJSONString(list);
    }

    @Override
    public void initData() {

    }
}