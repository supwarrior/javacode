package com.github.ddd.jpa;

import com.github.ddd.domainObject.BaseEntity;
import com.github.ddd.domainObject.ChildEntity;
import lombok.SneakyThrows;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Optional;

@CoreBeanCache
@Component
public class CoreJpaRepository extends JpaRepository {

    @SneakyThrows
    public <C extends ChildEntity> List<C> findChildEntities(final Class<C> subEntityType,
                                                             final Object masterEntityPrimaryKey) {
        final C instance = subEntityType.newInstance();
        instance.setReferenceKey(masterEntityPrimaryKey.toString());
        return super.findAll(Example.of(instance));
    }

    public <T extends BaseEntity> Optional<T> findOne(Example<T> example) {
        ExampleSpecification exampleSpecification = new JpaRepository.ExampleSpecification(example, EscapeCharacter.DEFAULT);
        T result = (T) this.getQuery(exampleSpecification, example.getProbeType(), Sort.unsorted()).getSingleResult();
        return this.isExampleValueEmpty((BaseEntity) example.getProbe()) ?
                Optional.empty() :
                Optional.of(result);

    }


    private <T> boolean isExampleValueEmpty(T example) {
        return allFields(example.getClass()).parallelStream().noneMatch((field) -> {
            ReflectionUtils.makeAccessible(field);
            return null != ReflectionUtils.getField(field, example);
        });
    }
}
