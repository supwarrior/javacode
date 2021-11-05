package com.github.ddd.controller;

import com.github.ddd.BaseCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductDataGenerator")
@Slf4j
public class ProductDataGenerator implements IProductRequestController {

    @Autowired
    private BaseCore baseCore;

    @Override
    public List initData() {
        String sql = "insert into t_product_request (id, prod_order_id,request_number) values (?, ?, ?)";
        Object[]  params = new Object[]{"0", "110",1};
        baseCore.insert(sql, params);
        return null;
    }
}
