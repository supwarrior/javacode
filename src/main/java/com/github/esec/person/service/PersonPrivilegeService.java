package com.github.esec.person.service;

public interface PersonPrivilegeService {
    boolean checkPersonHavePermissionInq(String userId, String functionID);
}
