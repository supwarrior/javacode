package com.github.service.impl;

import com.github.annotation.Component;
import com.github.javabean.Beans;
import com.github.model.ResultInfo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class SaveAndResultServiceImpl {

    /**
     * 模拟本地调用外部服务 SaveServiceImpl ResultServiceImpl
     */
    public String rpcService() throws Exception {
        SaveServiceImpl saveService = (SaveServiceImpl) Beans.getByType(SaveServiceImpl.class);
        ResultServiceImpl resultService = (ResultServiceImpl) Beans.getByType(ResultServiceImpl.class);
        String code = saveService.getCode(1);
        // 调用次数限制
        AtomicInteger atomicInteger = new AtomicInteger(0);
        String state = "";
        while (atomicInteger.incrementAndGet() < 6 && !state.equals("S")) {
            ResultInfo resultInfo = resultService.callback(code);
            state = resultInfo.getState();
            if ("E".equals(state)) {
                throw new RuntimeException(resultInfo.getMessage());
            }
            if (atomicInteger.get() == 5) {
                throw new RuntimeException("查询超时");
            }
        }
        return state;
    }

    /**
     * 本地业务逻辑处理
     *
     * @throws Exception
     */
    private void nativeHandle() throws Exception {
        String state = rpcService();
        System.out.println(state);
    }

    public static void main(String[] args) throws Exception {
        SaveAndResultServiceImpl saveAndResultService = (SaveAndResultServiceImpl) Beans.getByType(SaveAndResultServiceImpl.class);
        saveAndResultService.nativeHandle();
    }
}
