package com.github.analysis;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePostProcess {
    Class<? extends PostProcessPlanProxy> proxy() default PostProcessPlanProxy.class;
}
