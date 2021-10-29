package com.github.analysis;

import com.github.ddd.businessObject.BaseBO;

/**
 * @author 康盼Java开发工程师
 */
public interface Log extends BaseBO {


    /**
     * saveLog
     */
    void saveLog();

    /**
     * getEntity
     *
     * @return
     */
    Object getEntity();
}
