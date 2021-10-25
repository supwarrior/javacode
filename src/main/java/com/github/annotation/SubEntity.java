package com.github.annotation;

import com.github.code.MainEntity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>SubEntity .
 *
 * <p>change history:
 * <pre>
 * date               defect            person            comments
 * --------------------------------------------------------------------------------
 * 2021/9/13 13:29    ********          ZQI               create file.
 * </pre>
 *
 * <p> 该注解在 Core 框架中使用。如果自定义的数据库实体对象需要使用 Core 的框架来实现对数据的
 * 增、删、改、查, 那么该对象需要使用{@link SubEntity} 注解主动告诉 Core 自己是一个数据库对象。
 *
 * <p> <b>注意：</b></p>
 * <blockquote>
 * <ui>
 * <li>该注解必须作用于<font style="color: red">子表</font>对象上</li>
 * <li>该注解必须配合 {@link javax.persistence.Entity} 注解使用</li>
 * </ui>
 * </blockquote>
 *
 * @author ZQI
 * @version 1.0
 * @date 2021/9/13 13:29
 * @copyright 2021, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 * @since JDK1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SubEntity {

    /**
     * 指定子表对象关联的主表对象，主表对象上必须有 {@link MasterEntity} 注解
     * <p></p>
     *
     * @return 主表对象（不能为空）
     * @author zqi
     */
    Class<? extends MainEntity> masterEntity();

    /**
     * 当前实体对象是否为CDA的类
     * <p></p>
     *
     * @return 如果是返回true；默认返回false
     * @author zqi
     */
    boolean isCDA() default false;

    /**
     * 实体对象是否为Event的队列表
     * <p></p>
     *
     * @return 如果是返回true；默认返回false
     * @author zqi
     */
    boolean isEventQueue() default false;
}
