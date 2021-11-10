package com.github.mycim.event;

import com.github.ddd.factory.GenericCoreFactory;
import com.github.ddd.jpa.CoreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractManager {

    @Autowired
    protected GenericCoreFactory genericCoreFactory;


    @Autowired
    protected CoreJpaRepository coreJpaRepository;
}
