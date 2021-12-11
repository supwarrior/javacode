package com.github.env.event;

import com.github.ddd.SpringContextUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

public class SchedulePublisher implements SyncEventPublisher {
    @Configuration
    @ConditionalOnMissingBean(SyncEventPublisher.class)
    public static class AutoConfig {
        @Bean
        public SyncEventPublisher publisher() {
            return new SchedulePublisher();
        }
    }

//    @Scheduled(fixedRateString = "#{${sys.env.sync.interval:30} * 60000}")
    @Scheduled(fixedRate = 3000)
    public void poll() {
        SpringContextUtil.getApplicationContext().publishEvent(new SyncFromEvent(this));
    }
}
