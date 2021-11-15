package com.github.postProcessEvent.core;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope
public @interface OmMethod {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
