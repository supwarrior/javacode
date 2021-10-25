package com.github.annotation;

import com.github.code.ChildEntity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>MasterEntity .
 *
 * <p>change history:
 * <pre>
 * date               defect            person            comments
 * --------------------------------------------------------------------------------
 * 2021/9/13 13:25    ********          ZQI               create file.
 * </pre>
 *
 * <p> 该注解在 Core 框架中使用。如果自定义的数据库实体对象需要使用 Core 的框架来实现对数据的
 * 增、删、改、查, 那么该对象需要使用{@link MasterEntity} 注解主动告诉 Core 自己是一个数据库对象。
 *
 * <p> <b>注意：</b></p>
 * <blockquote>
 * <ui>
 * <li>该注解必须作用于<font style="color: red">主表</font>对象上</li>
 * <li>该注解必须配合 {@link javax.persistence.Entity} 注解使用</li>
 * </ui>
 * </blockquote>
 *
 * @author ZQI
 * @version 1.0
 * @date 2021/9/13 13:25
 * @copyright 2021, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 * @since JDK1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MasterEntity {

    /**
     * 指定主表对象下所有子表集合，子表对象上必须有{@link SubEntity} 注解
     * <p></p>
     *
     * @return 子表对象集合，默认为空
     * @author zqi
     */
    Class<? extends ChildEntity>[] subEntities() default {};
}
