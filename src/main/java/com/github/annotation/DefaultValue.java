package com.github.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 康盼Java开发工程师
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface DefaultValue {

    DefaultValue.Type type() default DefaultValue.Type.OTHER;

    /**
     * string value. Default "-"
     *
     * @return string
     */
    String stringValue() default "-";

    /**
     * double value. default 0.0
     *
     * @return double
     */
    double doubleValue() default 0D;

    /**
     * int value. default 0
     *
     * @return int
     */
    int intValue() default 0;

    /**
     * boolean value. default false.
     *
     * @return boolean
     */
    boolean boolValue() default false;

    /**
     * long value. default 0.
     *
     * @return long
     */
    long longValue() default 0L;

    /**
     * short value. default 0
     *
     * @return short
     */
    short shortValue() default 0;

    /**
     * char value. default '-'
     *
     * @return char
     */
    char charValue() default '-';

    public static enum Type {
        OTHER,
        INIT_TIME,
        CURRENT_TIME;

        private Type() {
        }
    }
}
