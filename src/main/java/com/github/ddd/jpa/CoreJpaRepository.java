package com.github.ddd.jpa;

import com.github.ddd.domainObject.ChildEntity;
import lombok.SneakyThrows;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
