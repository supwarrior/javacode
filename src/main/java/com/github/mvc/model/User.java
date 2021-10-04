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
}
