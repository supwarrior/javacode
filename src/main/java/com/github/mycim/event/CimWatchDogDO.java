package com.github.mycim.event;

import com.github.annotation.IdPrefix;
import com.github.ddd.domainObject.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
        name = "OMRESPSENTINEL"
)
@IdPrefix("OMRESPSENTINEL")
@Data
public class CimWatchDogDO extends BaseEntity {
    @Column(
            name = "TRX_ID",
            length = 10
    )
    private String transactionID;
    @Column(
            name = "RESP_SENTINEL",
            length = 20
    )
    private String watchDogName;
}
