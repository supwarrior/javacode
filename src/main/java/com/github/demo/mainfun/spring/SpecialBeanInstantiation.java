package com.github.demo.mainfun.spring;

import com.github.demo.mainfun.factory.DefaultUserFactory;
import com.github.demo.mainfun.factory.UserFactory;
import com.github.mvc.service.IUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiation {
     public static void main(String[] args) {
//         ServiceLoader<IUserService> serviceLoader =
//                 ServiceLoader.load(IUserService.class,Thread.currentThread().getContextClassLoader());

         String location = "classpath:bean-definitions-context.xml";
         BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);
         ServiceLoader<IUserService> serviceLoader = beanFactory.getBean("userFactoryServiceLoader",ServiceLoader.class);
         print(serviceLoader);

         ApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);
         AutowireCapableBeanFactory capableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
         UserFactory userFactory = capableBeanFactory.createBean(DefaultUserFactory.class);
         System.out.println(userFactory.createUser());

    }

    private static  <T extends IUserService> void print( ServiceLoader<T> serviceLoader) {
        Iterator<T> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            System.out.println(t.getSuperUserName());
        }
    }
}
