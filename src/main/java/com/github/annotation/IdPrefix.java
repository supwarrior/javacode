package com.github.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 康盼Java开发工程师
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IdPrefix {
    /**
     * <p>entity ID prefix</p>
     *
     * @author PlayBoy
     * @date 2018/9/27 11:20:27
     */
    String value() default "T";
}
