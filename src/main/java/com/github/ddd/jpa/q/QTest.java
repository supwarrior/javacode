package com.github.ddd.jpa.q;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class QTest {

    private EntityManager em;

    @Before
    public void begin() {
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        UserEntity user = new UserEntity();
        user.setId(0L);
        user.setName("康盼");
        em.persist(user);
        tx.commit();

    }

    @After
    public void last () {
        // 释放资源
        em.clear();
    }

    @Test
    public void testUserEntity() {
        UserEntity user = getUserByIdWithNativeQuery(0L);
        System.out.println(user);
    }

    public UserEntity getUserByIdWithNativeQuery(Long id) {
        Query namedQuery = em.createNamedQuery("UserEntity.findByUserId");
        namedQuery.setParameter("userId", id);
        return (UserEntity) namedQuery.getSingleResult();
    }

    public UserEntity getUserByIdWithCriteriaQuery(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        return em.createQuery(criteriaQuery.select(userRoot)
                .where(criteriaBuilder.equal(userRoot.get("id"),id)))
                .getSingleResult();
    }

    private EntityManager getEntityManager() {
        // 加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        // 通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        return em;
    }

}
