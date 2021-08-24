package com.github.model;

import com.github.annotation.Alias;
import com.github.annotation.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class User {
    @Alias("userId")
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
