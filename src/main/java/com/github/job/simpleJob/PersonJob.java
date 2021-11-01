package com.github.job.simpleJob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.github.job.JobConfig;
import lombok.extern.slf4j.Slf4j;

@JobConfig(name = "personJob")
@Slf4j
public class PersonJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("PersonJob begin...");
    }
}
