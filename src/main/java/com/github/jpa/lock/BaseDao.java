package com.github.jpa.lock;

import com.github.ddd.domainObject.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

    @Deprecated
    default void merge(T source, T target) throws Exception {
        if (source != null) {
            if (target == null) {
                target = source;
            }
        }
        BeanInfo baseInfo = Introspector.getBeanInfo(target.getClass());
        PropertyDescriptor[] propertyDescriptors = baseInfo.getPropertyDescriptors();
        int len = propertyDescriptors.length;
        for (int n = 0; n < len; ++n) {
            PropertyDescriptor descriptor = propertyDescriptors[n];
            if (null != descriptor.getWriteMethod()) {
                Object originalValue = descriptor.getReadMethod().invoke(target);
                if (null != originalValue) {
                    descriptor.getWriteMethod().invoke(source, originalValue);
                }
            }
        }
    }

}
