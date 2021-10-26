package com.github.common.core;

import com.github.common.util.BaseUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static com.github.common.util.BaseUtil.convertI;

/**
 * @author 康盼Java开发工程师
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class BaseCore {

    @PersistenceContext
    private EntityManager entityManager;


    public void insert(String sql, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0, n = args.length; i < n; i++) {
            if (args[i] == null) {
                nativeQuery.setParameter(i + 1, "");
            } else {
                nativeQuery.setParameter(i + 1, args[i]);
            }
        }
        nativeQuery.executeUpdate();
    }

    public List<Object[]> queryAll(String sql, Object... args) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        for (int i = 0, n = args.length; i < n; i++) {
            nativeQuery.setParameter(i + 1, args[i]);
        }
        return (List<Object[]>) nativeQuery.getResultList();
    }

    public int count(String sql, Object... args) {
        List<Object[]> objects = queryAll(sql, args);
        if (objects.size() == 0) {
            return 0;
        }
        if (objects.get(0) instanceof Object[]) {
            return convertI(objects.get(0)[0]);
        }
        return convertI(objects.get(0));
    }
}