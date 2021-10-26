package com.github.code;

import com.github.annotation.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class GenericCorePool {

    private static final ThreadLocal<Map<String, CimBO>> BIZ_OBJECT_LOCAL = new ThreadLocal();

    public GenericCorePool() {
    }

    public <T extends CimBO> T getBO(Class<T> type, Long primaryKey) {
        Map<String, CimBO> pool = getPool();
        CimBO cimBO =  pool.get(primaryKey);
        if (cimBO == null) {
            return null;
        } else if (cimBO.isDirty()) {
            pool.remove(primaryKey);
            return null;
        } else {
            return (T) cimBO;
        }
    }

    private static Map<String, CimBO> getPool() {
        return (Map) Optional.ofNullable(BIZ_OBJECT_LOCAL.get()).orElseGet(() -> {
            HashMap<String, CimBO> map = new HashMap(256);
            BIZ_OBJECT_LOCAL.set(map);
            return map;
        });
    }

    public Map<String, CimBO> getAllCachedBizObjects() {
        return getPool();
    }

    public void markDirtyIfCached(String primaryKey) {
        Optional.ofNullable(getPool().get(primaryKey)).ifPresent(CimBO::makeDirty);
    }

    public <T extends CimBO> void cacheBO(T cimBO) {
        getPool().put(cimBO.getPrimaryKey(), cimBO);
    }

    public static void clear() {
        Optional.ofNullable(getPool()).ifPresent((pool) -> {
            pool.forEach((key, bo) -> {
                if (bo instanceof AbstractCimBO) {
                    AbstractCimBO<?> cimBO = (AbstractCimBO)bo;
                    cimBO.makeDirty();
                }

            });
        });
        BIZ_OBJECT_LOCAL.remove();
    }
}