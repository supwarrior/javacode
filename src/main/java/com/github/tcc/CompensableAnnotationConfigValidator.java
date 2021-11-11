package com.github.tcc;

import com.github.annotation.Compensable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 扫描得到所有注解了Compensable 的类，同时解析出来cancel，confirm对应的类
 * 参考 https://github.com/liuyangming/ByteTCC/wiki/User-Guide-0.5.x
 *
 * @author 康盼Java开发工程师
 */
@Component
@Slf4j
public class CompensableAnnotationConfigValidator implements SmartInitializingSingleton, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;


    public CompensableAnnotationConfigValidator(ApplicationContext applicationContext, BeanFactory beanFactory) {
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 没有构造的话 需要赋值 这里可以不用赋值
        this.beanFactory = beanFactory;
    }

    /**
     * spring容器管理的所有单例对象（非懒加载对象）初始化完成之后调用的回调接口
     */
    @SneakyThrows
    @Override
    public void afterSingletonsInstantiated() {
        String[] beanNameArray = this.applicationContext.getBeanDefinitionNames();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry)this.beanFactory;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        for(int i = 0; beanNameArray != null && i < beanNameArray.length; ++i) {
            String beanName = beanNameArray[i];
            BeanDefinition beanDef = registry.getBeanDefinition(beanName);
            String className = beanDef.getBeanClassName();
            log.info("afterSingletonsInstantiated className:{}",className);
            if (!StringUtils.isBlank(className)) {
                Class clazz = cl.loadClass(className);
                Compensable compensable =  (Compensable)clazz.getAnnotation(Compensable.class);
                if (compensable != null) {
                    String confirmableKey = compensable.confirmableKey();
                    String cancellableKey = compensable.cancellableKey();
                    Object confirmableService = null;
                    Object cancellableService = null;
                    if (StringUtils.isNotBlank(confirmableKey)) {
                        confirmableService = applicationContext.getBean(confirmableKey);
                    }
                    if (StringUtils.isNotBlank(cancellableKey)) {
                        cancellableService = applicationContext.getBean(cancellableKey);
                    }
                    Class interfaceClass = compensable.interfaceClass();
                    if (interfaceClass != null && interfaceClass.isInterface()) {
                        Method[] methodArray = interfaceClass.getDeclaredMethods();
                        for(int j = 0; j < methodArray.length; ++j) {
                            Method interfaceMethod = methodArray[j];
                            String methodName = interfaceMethod.getName();
                            log.info("methodName:{}",methodName);
                            Class<?>[] parameterTypes = interfaceMethod.getParameterTypes();
                            int len = parameterTypes.length;
                            Object[] args = new Object[len];
                            for (int k = 0; k < len; ++k) {
                                args[k] = parameterTypes[k].newInstance();
                            }
                            if (confirmableService != null) {
                                interfaceMethod.invoke(confirmableService, args);
                            }
                            if (cancellableService != null) {
                                interfaceMethod.invoke(cancellableService, args);
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 没有构造的话 需要赋值 这里可以不用赋值
        this.applicationContext = applicationContext;
    }
}