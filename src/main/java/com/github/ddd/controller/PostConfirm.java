package com.github.ddd.controller;

import com.github.ddd.BaseCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 康盼Java开发工程师
 */
@Service("PostConfirm")
@Slf4j
public class PostConfirm implements IPostController {


    @Autowired
    private BaseCore baseCore;

    @Override
    public void callBack() {

        String sql = "insert into t_person (id, org_id, email_id, passwd, tel_contact_no, user_id) values (?, ?,?,?,?,?)";
        Object[] params = {"0", "上扬", "work@163.com", "123", "13728897992", "u007"};
        baseCore.insert(sql, params);
    }
}