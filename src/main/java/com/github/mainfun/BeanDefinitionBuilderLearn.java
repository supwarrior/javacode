package com.github.mainfun;

import com.github.mvc.model.User;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 *  bean 的注册方法
 */
public class BeanDefinitionBuilderLearn {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDefinitionBuilderLearn.class);
        // 命名方式注入
        registryBean("pan",applicationContext,User.class,
                Splitter.on(";").withKeyValueSeparator(":").split("sex:boy;name:pan.kang"));
        // 非命名方式注入 自动生成 bean name
        /**
         * @see BeanDefinitionReaderUtils#uniqueBeanName
         */
        registryBean(null,applicationContext,User.class,
                Splitter.on(";").withKeyValueSeparator(":").split("sex:boy;name:pan.kang"));
        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(User.class));
        applicationContext.close();
    }

    public static void registryBean(String beanName, BeanDefinitionRegistry beanDefinitionRegistry, Class<?> beanClass, Map<String, String> args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        for (Map.Entry<String, String> entry : args.entrySet()) {
            beanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
        }
        if (StringUtils.hasText(beanName)) {
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),beanDefinitionRegistry);
        }
    }

}
