package com.github.demo.mainfun.spring;

import com.github.mvc.model.User;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.Map;

/**
 * IOC
 * 依赖注入 依赖查找
 * 依赖来源：自定义bean 内建依赖 内建bean
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
        String location = "classpath:spring-bean.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository.getBeanFactory());
        // false
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        ObjectFactory<User> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        // true (ApplicationContext  BeanFactory)
        System.out.println(userRepository.getApplicationContextObjectFactory().getObject() == beanFactory);

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        reader.loadBeanDefinitions(location);
        if (defaultListableBeanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = defaultListableBeanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的 user 集合对象：" + users);
        }

    }
}
