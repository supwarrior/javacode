package com.github.ddd.jpa.esec;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotGeneratedMapParam {
    boolean value() default false;
}
