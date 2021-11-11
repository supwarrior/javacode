package com.github.esec.core;


import com.github.esec.entity.User;
import com.github.jpa.lock.ObjectIdentifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBasicEntity  extends BaseEntity {

    @Column(name = "CLAIM_TIME")
    private Timestamp lastClaimedTimeStamp;

    @Column(name = "CLAIM_USER_ID", length = 64)
    private String lastClaimedUserID;

    @Column(name = "CLAIM_USER_OBJ", length = 64)
    private String lastClaimedUserObj;

    @PrePersist
    @PreUpdate
    private void setBasic() {
        this.lastClaimedTimeStamp = new Timestamp(System.currentTimeMillis());
        User user = LocalContextHolder.getInstance().getContext();
        ObjectIdentifier userID = user.getUserID();
        if (Objects.nonNull(userID)) {
            this.lastClaimedUserID = userID.getValue();
            this.lastClaimedUserObj = userID.getReferenceKey();
        }
    }
}
