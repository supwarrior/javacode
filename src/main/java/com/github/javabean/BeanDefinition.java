package com.github.javabean;

/**
 * @author 康盼Java开发工程师
 */
public class BeanDefinition {

    private Class beanClass;

    private BeanProperties properties;

    public BeanDefinition(Class beanClass, BeanProperties properties) {
        this.beanClass = beanClass;
        this.properties = properties != null ? properties : new BeanProperties();
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.properties = new BeanProperties();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanProperties getProperties() {
        return properties;
    }

    public void setProperties(BeanProperties properties) {
        this.properties = properties;
    }
}
