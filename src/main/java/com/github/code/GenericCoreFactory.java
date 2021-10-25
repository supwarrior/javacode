package com.github.code;

import com.github.annotation.CoreBeanCache;
import org.springframework.stereotype.Component;

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

    @Override
    public <T extends CimBO> T getBO(Class<T> boClazz, BaseEntity entity) {
        return null;
    }
}
