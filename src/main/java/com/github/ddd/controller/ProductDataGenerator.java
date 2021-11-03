package com.github.ddd.controller;

import com.github.ddd.BaseCore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductDataGenerator")
@Slf4j
public class ProductDataGenerator implements IProductRequestController {

    @Autowired
    private BaseCore baseCore;

    @Override
    public void initData() {
        String sql = "insert into t_product_request (id, prod_order_id) values (?, ?)";
        Object[]  params = new Object[]{"0", "110"};
        baseCore.insert(sql, params);
    }
}
