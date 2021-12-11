package com.github.demo.mainfun.spring;

import com.github.mvc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 (instantiation)
 * 常规方式：配置元信息：XML、java注解 和 JavaApi
 *      通过构造器
 *      通过静态工厂方法
 *      通过 Bean 工厂方法
 *      通过 FactoryBean
 *
 * 特殊方法
 *      通过 ServiceLoadFactoryBean
 *      通过 AutowireCapableBeanFactory#CreateBean(java.lang.Class,int,boolean)
 *      通过 BeanDefinitionRegistry#registerBeanDefiniton(String,BeanDefinition)
 */
public class BeanInstanceLearn {
    public static void main(String[] args) {
        String location = "classpath:bean-definitions-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);
        System.out.println(beanFactory.getBean("user-by-static-method"));

        User user = (User) beanFactory.getBean("user-by-factory-bean",User.class);
        System.out.println(user);
    }
}
