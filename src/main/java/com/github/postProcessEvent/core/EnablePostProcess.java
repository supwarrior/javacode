package com.github.postProcessEvent.core;

import com.github.postProcessEvent.core.aspect.PostProcessPlanProxy;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnablePostProcess {

    Class<? extends PostProcessPlanProxy> proxy() default PostProcessPlanProxy.class;

}
