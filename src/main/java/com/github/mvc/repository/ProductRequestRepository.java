package com.github.mvc.repository;


import com.github.ddd.domainObject.ProductRequestDO;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ProductRequestRepository extends CrudRepository<ProductRequestDO, String> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("from ProductRequestDO  where id = :id")
    Optional<ProductRequestDO> findById(String id);
}

