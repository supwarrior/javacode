package com.github.env.app.method;

import com.github.ddd.SpringContextUtil;
import com.github.env.app.entity.EnvVariable;
import com.github.env.core.EnvironmentVariable;
import com.github.env.core.SwitchPropertySource;
import com.github.env.event.SyncToEvent;
import com.github.postProcessEvent.core.OmMethod;
import org.springframework.beans.factory.annotation.Autowired;

@OmMethod
public class EnvironmentMethod implements IEnvironmentMethod {

    @Autowired
    private SwitchPropertySource propertySource;


    @Override
    public void environmentSet(EnvVariable envVariable) {
        EnvironmentVariable environmentVariable = propertySource.find(envVariable.getEnvName());
        environmentVariable.setValue(envVariable.getEnvValue());
        environmentVariable.makeSyncToRequired();
        SyncToEvent syncToEvent = new SyncToEvent(this);
        SpringContextUtil.getApplicationContext().publishEvent(syncToEvent);
    }
}
