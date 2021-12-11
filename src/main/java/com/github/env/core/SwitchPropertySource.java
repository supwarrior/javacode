package com.github.env.core;

import java.util.List;

public interface SwitchPropertySource {

    void refreshSource(EnvironmentVariable variable);

    EnvironmentVariable find(String name);

    List<EnvironmentVariableDO> allFromSource();

    List<EnvironmentVariable> allFromLocal();

    void setIncludes(List<Class<? extends EnvironmentVariable>> includes);
}
