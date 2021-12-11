package com.github.demo.mainfun.factory;

import com.github.mvc.model.User;

public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
