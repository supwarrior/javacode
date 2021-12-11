package com.github.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现自己程序的健康状态
 */
@Component
public class Health implements HealthIndicator, MeterBinder {

    private Counter counter;


    @Override
    public org.springframework.boot.actuate.health.Health health() {
        Map<String,Object> map = new HashMap<>();
        map.put("项目名","java code");
        map.put("作者","康盼");
        map.put("项目描述","JAVA 学习项目");
        counter.increment();
        map.put("查看次数",this.counter.count());
        return org.springframework.boot.actuate.health.Health.up().withDetails(map).build();
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("counter.number");
    }
}
