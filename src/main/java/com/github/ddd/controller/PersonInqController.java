package com.github.ddd.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.ddd.BaseCore;
import com.github.ddd.businessObject.BaseBO;
import com.github.ddd.domainObject.Person;
import com.github.ddd.factory.BaseCoreFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonInqController implements ApplicationRunner {

    @Autowired
    private BaseCoreFactory baseCoreFactory;

    @Autowired
    private BaseCore baseCore;

    @GetMapping(path = "/person/inq/{id}")
    public String getPersonById(@PathVariable(value = "id") String id) {
        Person person = baseCoreFactory.getBO(Person.class, id);
        person.business();
        return JSONObject.toJSONString(person);
    }

    @GetMapping(path = "/person_list/inq/{identifier}")
    public String getPersonByNamedIdentifier(@PathVariable(value = "identifier") String identifier) {
        List<BaseBO> list = baseCoreFactory.getBOByNamedIdentifier(Person.class, identifier);
        return JSONObject.toJSONString(list);
    }


    /**
     * id flush 赋值
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String sql = "insert into t_person (id, org_id, email_id, passwd, tel_contact_no, user_id) values (?, ?,?,?,?,?)";
        Object[] params = {"0", "上扬", "work@163.com", "123", "13728897992", "u007"};
        baseCore.insert(sql, params);
    }
}