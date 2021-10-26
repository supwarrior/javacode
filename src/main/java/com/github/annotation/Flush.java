package com.github.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 康盼Java开发工程师
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Flush {
    Flush.Scope scope() default Flush.Scope.MAIN;

    public static enum Scope {
        ALL,
        MAIN,
        ATTRIBUTE;

        private Scope() {
        }
    }
}
