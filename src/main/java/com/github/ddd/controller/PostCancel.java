package com.github.ddd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 康盼Java开发工程师
 */
@Service("PostCancel")
@Slf4j
public class PostCancel implements IPostController {

    @Override
    public void callBack() {
        log.info("PostCancel callBack");
    }
}