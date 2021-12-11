package com.github.env.app.service;

import com.github.env.app.entity.EnvVariable;
import com.github.env.app.method.IEnvironmentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private IEnvironmentMethod environmentMethod;

    public void envModifyReq(EnvVariable envVariable) {
        environmentMethod.environmentSet(envVariable);
    }
}
