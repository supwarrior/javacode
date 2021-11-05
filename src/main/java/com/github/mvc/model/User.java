package com.github.mvc.model;

import com.github.annotation.Alias;
import com.github.annotation.Component;
import lombok.Data;

/**
 * @author 康盼Java开发工程师
 */

@Component
@Data
public class User {
    @Alias("userId")
    private Long id;
    private String name;
    private Integer age;
    private String sex;

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("pan.kang");
        user.setAge(100);
        user.setSex("男");
        return user;
    }
}
