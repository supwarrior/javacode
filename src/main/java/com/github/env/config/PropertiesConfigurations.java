package com.github.env.config;

import com.github.env.core.SourceType;
import com.github.env.core.SwitchPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfigurations {

    @Value("${sys.env.source:DBConfig}")
    private String source;

    @Bean
    @ConditionalOnMissingBean(SwitchPropertySource.class)
    public SwitchPropertySource propertySource() throws Exception{
        return SourceType.valueOf(source).getSourceType().newInstance();
    }
}
