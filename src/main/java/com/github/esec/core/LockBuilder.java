package com.github.esec.core;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LockBuilder {

    @Autowired
    private RedissonClient redissonClient;

    public LockInstance getInstance() {
        return new LockInstance(redissonClient);
    }

}
