package com.github.javabean;

import com.github.javabean.register.AutoConfigureRegister;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动注册自定义注解的bean
 *
 * @author 康盼Java开发工程师
 */
@Configuration
@Import(AutoConfigureRegister.class)
public class BeanAutoConfig {
}
