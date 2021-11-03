package com.github.jpa.lock;

import java.util.List;
import java.util.Optional;

public interface LockDao extends BaseDao<LockDO> {

    Optional<LockDO> findByObjectKeyAndAttributeKey(String objectKey, String attributeKey);

    List<LockDO> findAllByObjectKeyOrderByAttributeKey(String objectKey);
}
