package com.github.javabean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 康盼Java开发工程师
 */
public class BeanProperties {

    private final List<BeanProperty> properties = new ArrayList<>();

    public List<BeanProperty> getProperties() {
        return properties;
    }

    public void add(BeanProperty beanProperty) {
        properties.add(beanProperty);
    }
}
