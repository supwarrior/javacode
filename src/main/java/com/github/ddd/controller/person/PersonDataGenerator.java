package com.github.ddd.controller.person;

import com.github.ddd.BaseCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 康盼Java开发工程师
 */
@Service("PersonDataGenerator")
@Slf4j
public class PersonDataGenerator implements IPersonController {

    @Autowired
    private BaseCore baseCore;

    @Override
    public void initData() {
        String sql = "insert into t_person (id, org_id, email_id, passwd, tel_contact_no, user_id) values (?, ?,?,?,?,?)";
        Object[] params = {"0", "上扬", "work@163.com", "123", "13728897992", "u007"};
        baseCore.insert(sql, params);

        sql = "insert into t_sub_person (id, org_id, email_id, passwd, tel_contact_no, user_id, REFKEY) values (?, ?,?,?,?,?,?)";
        params = new Object[]{"0", "上扬", "work@163.com", "123", "13728897992", "u008","0"};
        baseCore.insert(sql, params);
    }
}