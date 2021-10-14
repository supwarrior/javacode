package com.github.mvc.repository;

import com.github.javabean.Beans;
import com.github.mvc.model.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
public class AnnotationApplicationContextRepository {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextRepository 作为配置类 相当于加了 @Configuration
        annotationConfigApplicationContext.register(AnnotationApplicationContextRepository.class);
        // 刷新上下文
        annotationConfigApplicationContext.refresh();
        if (annotationConfigApplicationContext instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = annotationConfigApplicationContext;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找所有的 User 集合对象：" + users);

        }
    }

    @Bean(name = {"bean-user"})
    public User user() {
        return (User) Beans.getByName("user");
    }
}
