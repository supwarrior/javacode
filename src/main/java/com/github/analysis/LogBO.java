package com.github.analysis;

import com.github.annotation.Core;
import com.github.annotation.Flush;
import com.github.ddd.businessObject.AbstractBO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 康盼Java开发工程师
 */
@Core
@ToString(callSuper = true)
@Slf4j
public class LogBO extends AbstractBO<LogDO> implements Log {

    public LogBO(LogDO entity) {
        super(entity);
    }

    @Override
    @Flush
    public void saveLog() {

    }
}