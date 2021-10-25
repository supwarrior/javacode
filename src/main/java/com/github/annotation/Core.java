package com.github.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 13:16
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@org.springframework.stereotype.Component
@Scope("prototype")
public @interface Core {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
