package com.github.esec.person.service;

import com.github.esec.entity.dto.PersonPrivilegeGroupDTO;
import com.github.esec.person.repository.PersonPrivilegeRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.github.esec.bus.QPersonPrivilegeGroupDO.personPrivilegeGroupDO;


@Slf4j
@Service
public class PersonPrivilegeServiceImpl implements PersonPrivilegeService {

    @Autowired
    private PersonPrivilegeRepository personPrivilegeRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public boolean checkPersonHavePermissionInq(String userId, String functionID) {
        JPAQueryFactory query = personPrivilegeRepository.generateQuery();
        // spring data QueryDSL
        Predicate predicate = personPrivilegeGroupDO.dKey.eq("test");

        // 单条件查询
        query.selectFrom(personPrivilegeGroupDO).where(personPrivilegeGroupDO.dKey.eq("test")).fetchOne();
        // 所有记录
        query.selectFrom(personPrivilegeGroupDO).fetch();

        // join 查询 排序
        query.selectFrom(personPrivilegeGroupDO)
                .innerJoin(personPrivilegeGroupDO)
                .on(personPrivilegeGroupDO.dKey.eq("test")).orderBy(personPrivilegeGroupDO.id.desc())
                .fetch();


        // 分页查询单表
        Predicate pre = personPrivilegeGroupDO.id.lt("100");
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        PageRequest pr = new PageRequest(1, 10, sort);
        personPrivilegeRepository.findAll(pre, pr);

        // 使用 Projections.bean dto 转化
        query.select(
                        Projections.bean(PersonPrivilegeGroupDTO.class, personPrivilegeGroupDO.id.as("id")))
                .from(personPrivilegeGroupDO)
                .where(predicate)
                .fetch();

        // 使用 Projections.fields dto 转化 set方法
        query.select(
                        Projections.fields(PersonPrivilegeGroupDTO.class, personPrivilegeGroupDO.id.as("id")))
                .from(personPrivilegeGroupDO)
                .where(predicate)
                .fetch();

        // 构造方法
        query.select(
                        Projections.constructor(PersonPrivilegeGroupDTO.class, personPrivilegeGroupDO.id.as("id")))
                .from(personPrivilegeGroupDO)
                .where(predicate)
                .fetch();

        personPrivilegeRepository.findOne(predicate);

        return false;
    }
}
