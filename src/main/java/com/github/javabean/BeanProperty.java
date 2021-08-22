package com.github.javabean;

/**
 * @author 康盼Java开发工程师
 */
public class BeanProperty {

    private final String name;

    private final Object value;

    public BeanProperty(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
