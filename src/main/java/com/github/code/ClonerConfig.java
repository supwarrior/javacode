package com.github.code;

import com.github.annotation.Component;
import com.rits.cloning.Cloner;
import org.springframework.context.annotation.Bean;

/**
 * @author 康盼Java开发工程师
 * https://github.com/kostaskougios/cloning/blob/master/wiki/Usage.md
 */
@Component
public class ClonerConfig {
    @Bean
    public Cloner getCloner(){
        Cloner cloner = new Cloner();
        cloner.setDumpClonedClasses(false);
        return cloner;
    }
}