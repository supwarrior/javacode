package com.github.ddd.controller;

import com.github.common.cons.Response;
import com.github.ddd.CoreAttribute;
import com.github.ddd.domainObject.SubPersonDO;
import com.github.ddd.factory.BaseCoreFactory;
import com.github.ddd.jpa.CoreJpaRepository;
import com.github.ddd.jpa.JpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("/jpa")
@Slf4j
public class JpaAttributeController {

    @Autowired
    private CoreJpaRepository coreJpaRepository;

    @Autowired
    private BaseCoreFactory baseCoreFactory;

    @PersistenceContext
    protected EntityManager em;

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public  String find() {
        CoreAttribute attribute = new CoreAttribute(this.coreJpaRepository, SubPersonDO.class, "0");
        Field field = CoreAttribute.findField(SubPersonDO.class, "USER_ID");
        CoreAttribute.DictionaryContent dictionaryContent = attribute.new DictionaryContent(field);

        // like this
        try {
            // em is jpa EntityManager
            SubPersonDO subPersonDO = new SubPersonDO();
            subPersonDO.setUserID("u008");
            subPersonDO.setReferenceKey("0");
            Example<SubPersonDO> example = Example.of(subPersonDO);
            CriteriaBuilder builder = this.em.getCriteriaBuilder();
            CriteriaQuery<SubPersonDO> query = builder.createQuery(SubPersonDO.class);
            Root<SubPersonDO> root = query.from(SubPersonDO.class);
            Specification<SubPersonDO> specification =  new JpaRepository.ExampleSpecification(example, EscapeCharacter.DEFAULT);
            Predicate predicate = specification.toPredicate(root, query, builder);
            query.where(predicate);
            query.select(root);
            List<SubPersonDO> resultList =  this.em.createQuery(query).getResultList();
            log.info("obj{}",resultList);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return Response.createSucc("",dictionaryContent.get("u008")).getMessage();
    }

}
