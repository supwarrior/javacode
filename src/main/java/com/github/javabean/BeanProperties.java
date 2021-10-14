package com.github.javabean;

import com.github.resource.PropertyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void setDefault(String name) {
        try {
            Map<String, String> map = PropertyUtil.getPropertyByResourceBundle(name, "zh");
            for (Map.Entry<String, String> element : map.entrySet()) {
                BeanProperty beanProperty = new BeanProperty(element.getKey(), element.getValue());
                properties.add(beanProperty);
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
