package com.github.mvc.repository;

import com.github.mvc.model.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

/**
 * 依赖注入 依赖查找
 *
 * @author 康盼Java开发工程师
 */
@Data
public class UserRepository {
    private Collection<User> users;
    /**
     * 内建非 bean
     */
    private BeanFactory beanFactory;

    private ObjectFactory<User> objectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository.getBeanFactory());
        // false
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        // true (ApplicationContext  BeanFactory)
        System.out.println(userRepository.getApplicationContextObjectFactory().getObject() == beanFactory);
    }
}
