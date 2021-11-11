package com.github.esec.entityListeners.controller;

import com.github.esec.entityListeners.controller.Admin;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * https://www.baeldung.com/jpa-entity-lifecycle-events
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/entity-audit-listener.html
 *
 */
@Slf4j
public class AuditTrailListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Admin user) {
        if (user.getId() == 0) {
            log.info("[USER AUDIT] About to add a user");
        } else {
            log.info("[USER AUDIT] About to update/delete user: " + user.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Admin user) {
        log.info("[USER AUDIT] add/update/delete complete for user: " + user.getId());
    }

    @PostLoad
    private void afterLoad(Admin user) {
        log.info("[USER AUDIT] user loaded from database: " + user.getId());
    }
}
