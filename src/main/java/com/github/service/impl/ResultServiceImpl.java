package com.github.service.impl;

import com.github.annotation.Component;
import com.github.model.ResultInfo;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class ResultServiceImpl {

    /**
     * 模拟调用 SaveServiceImpl保存方法异步返回的信息
     *
     * @return
     */
    public ResultInfo callback(String code) throws Exception {
        System.out.println("根据标识：" + code + "查询结果");
        ResultInfo result = new ResultInfo();
        String[] state = new String[]{"V", "V","V","V","V","V","S", "S","E", "O"};
        String[] info = new String[]{"正在处理中","正在处理中","正在处理中","正在处理中","正在处理中","正在处理中", "保存成功", "保存成功","保存失败,code不满足条件", "未开始"};
//        SplittableRandom random = new SplittableRandom();
        Random random = new Random(System.currentTimeMillis());
        int n = random.nextInt(state.length);
//        int n = random.nextInt(state.length);
//        n = ThreadLocalRandom.current().nextInt(state.length);
        result.setState(state[n]);
        result.setMessage(info[n]);
        return result;
    }

    public static void main(String[] args) throws Exception {
        ResultServiceImpl resultService = new ResultServiceImpl();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (atomicInteger.get() < 10) {
            atomicInteger.incrementAndGet();
            ResultInfo result = resultService.callback("Q-W-E-R-T-Y-U-I-O-P");
            System.out.println(result.getState());
        }

    }
}
