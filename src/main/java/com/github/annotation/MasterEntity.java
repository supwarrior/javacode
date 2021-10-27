package com.github.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解在 Core 框架中使用。如果自定义的数据库实体对象需要使用 Core 的框架来实现对数据的
 *  增、删、改、查, 那么该对象需要使用{@link MasterEntity} 注解主动告诉 Core 自己是一个数据库对象。
 *
 * @author 康盼Java开发工程师
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MasterEntity {

}
