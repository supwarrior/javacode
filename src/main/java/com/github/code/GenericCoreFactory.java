package com.github.code;

import com.github.annotation.CoreBeanCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * description:
 * <p>
 * change history:
 * date             defect#             person             comments
 * ---------------------------------------------------------------------------------------------------------------------
 * 2021/10/25     ********            pan.kang                create file
 *
 * @author: pan.kang
 * @date: 2021/10/25 15:27
 * @copyright: 2020, FA Software (Shanghai) Co., Ltd. All Rights Reserved.
 */
@CoreBeanCache
@Component
/**
 * JDBC	        Hibernate	     JPA
 * DataSource	SessionFactory	 EntityManagerFactory
 * Connection	Session	         EntityManager
 */
public class GenericCoreFactory extends AbstractCoreFactory {

    @Autowired
    private GenericCorePool genericCorePool;

    @Autowired
    private CoreBeanMapping coreBeanMapping;

    @Autowired
    private CoreJpaRepository coreJpaRepository;

    @Autowired
    private CoreHelper coreHelper;

    /**
     *  CimPerson cimPersonBO = baseCoreFactory.getBO(CimPerson.class,userID.getValue());
     *
     * @param boClazz
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T extends CimBO> T getBO(Class<T> boClazz, BaseEntity entity) {
        if (null == entity) {
            return null;
        } else if (StringUtils.isEmpty(entity.getId())) {
            throw new RuntimeException("The Primary Key is null");
        } else {
            return this.getCimBO(boClazz, entity);
        }
    }

    private <T extends CimBO> T getCimBO(Class<T> boClazz, BaseEntity entity) {
        if (null == entity) {
            return null;
        } else {
            Long primaryKey = entity.getId();
            return  Optional.ofNullable(this.genericCorePool.getBO(boClazz, primaryKey)).orElseGet(() -> {
                T cimBO =  this.coreHelper.generateBO(boClazz, entity);
                this.genericCorePool.cacheBO(cimBO);
                return cimBO;
            });
        }
    }
}
