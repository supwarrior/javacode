package com.github.env.core;


import com.github.env.core.DBSource;
import com.github.env.core.SwitchPropertySource;

public enum SourceType {

    DBConfig(DBSource.class),
//    SysConfig(SysSource.class),
//    RedisConfig(RedisSource.class),
//    ApolloConfig(AppSource.class),
    ;
    private final Class<? extends SwitchPropertySource> sourceType;

    SourceType(Class<? extends SwitchPropertySource> sourceType) {
        this.sourceType = sourceType;
    }

    public Class<? extends SwitchPropertySource> getSourceType() {
        return sourceType;
    }
}
