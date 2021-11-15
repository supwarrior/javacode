package com.github.postProcessEvent.dao;

import com.github.jpa.lock.BaseDao;
import com.github.postProcessEvent.entity.CimPostProcessPatternDefinitionDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostProcessPatternDefinitionDao extends BaseDao<CimPostProcessPatternDefinitionDO> {


    @Query(
            nativeQuery = true,
            value = "SELECT A.* FROM" +
                    "OSPPPTNDEF A, OSPPTRXDEF B" +
                    "WHERE A.PATTERN_ID = B.PATTERN_ID" +
                    "AND B.TRX_ID = :transactionId ORDER BY A.IDX_NO"
    )
    List<CimPostProcessPatternDefinitionDO> findAllByTransactionId(String transactionId);
}
