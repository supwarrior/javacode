package com.github.postProcessEvent.core.executor;

import com.github.postProcessEvent.model.AvailablePhase;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 声明后置处理器
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface PostProcessTaskHandler {
    @AliasFor(annotation = Component.class)
    String value() default "";
    AvailablePhase available() default AvailablePhase.ALL;
    boolean isNextOperationRequired() default false;

}
