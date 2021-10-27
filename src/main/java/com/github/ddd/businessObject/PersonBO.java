package com.github.ddd.businessObject;


import com.github.annotation.Core;
import com.github.annotation.Flush;
import com.github.ddd.domainObject.Person;
import com.github.ddd.domainObject.PersonDO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 执行 PersonBO 领域的 业务逻辑代码
 *
 * @author 康盼Java开发工程师
 */
@Core
@ToString(callSuper = true)
@Slf4j
public class PersonBO extends AbstractBO<PersonDO> implements Person {

    public PersonBO(PersonDO entity) {
        super(entity);
    }

    /**
     * 模拟执行业务逻辑
     */
    @Override
    @Flush
    public void business() {
        log.info("执行了抽象类的 flushMain 方法");
    }
}