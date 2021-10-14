package com.github.mvc.repository;

import com.github.annotation.Component;
import com.github.javabean.Beans;
import com.github.mvc.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName
 *
 * @author 康盼Java开发工程师
 */
@Import(AnnotationBeanDefinitionRepository.Config.class)
public class AnnotationBeanDefinitionRepository {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionRepository.class);
        applicationContext.refresh();
        Map<String, Config> configBeans = applicationContext.getBeansOfType(Config.class);
        System.out.println("configBeans: " + configBeans);
    }

    @Component
    public static class Config {

        @Bean(name = {"superUser"})
        public User user() {
            return (User) Beans.getByName("user");
        }
    }
}
