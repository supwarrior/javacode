package com.github.service.impl;

import com.github.annotation.Component;
import com.github.model.ResultInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class ProxySaveServiceImpl implements InvocationHandler {

    private SaveServiceImpl saveService = new SaveServiceImpl();

    private  ResultServiceImpl resultService = new ResultServiceImpl();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String state = new String();
        if ("getCode".equals(method.getName())) {
            state = (String) method.invoke(saveService, args);
            AtomicInteger atomicInteger = new AtomicInteger(0);
            while (atomicInteger.incrementAndGet() < 6 && !state.equals("S")) {
                ResultInfo resultInfo = resultService.callback((String)args[0]);
                state = resultInfo.getState();
                if ("E".equals(state)) {
                    throw new RuntimeException(resultInfo.getMessage());
                }
                if (atomicInteger.get() == 5) {
                    throw new RuntimeException("查询超时");
                }
            }
        }

        return state;
    }
}
