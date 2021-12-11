package com.github.env.core;

import com.github.env.StandardProperties;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractSwitchPropertySource implements SwitchPropertySource {

    private final Map<String, EnvironmentVariable> properties = new HashMap<>(1024);

    /*
     * initialize the enums into the properties map
     */
    {
        properties.putAll(Arrays.stream(StandardProperties.values())
                .filter(ele -> !ele.isUndefined())
                .collect(Collectors.toMap(EnvironmentVariable::getName, ele -> ele)));
    }

    @Override
    public EnvironmentVariable find(String name) {
        return properties.getOrDefault(name.toUpperCase(), StandardProperties.Undefined);
    }

    @Override
    public List<EnvironmentVariable> allFromLocal() {
        return new ArrayList<>(properties.values());
    }


    private EnvironmentVariable[] covertToProperties(Class<? extends EnvironmentVariable> clz) {
        Method method = ReflectionUtils.findMethod(clz, "values");
        if(method == null) return null;
        Object values = ReflectionUtils.invokeMethod(method, null);
        if (values == null || !values.getClass().isArray()) return null;
        int len = Array.getLength(values);
        EnvironmentVariable[] result = new EnvironmentVariable[len];
        for (int i = 0; i < len; i++) {
            result[i] = (EnvironmentVariable) Array.get(values, i);
        }
        return result;
    }

    @Override
    public void setIncludes(List<Class<? extends EnvironmentVariable>> includes) {
        if (includes.isEmpty()) return;
        Map<String, EnvironmentVariable> includesMap = includes.stream()
                .filter(Enum.class::isAssignableFrom)
                .filter(varType -> varType != StandardProperties.class)
                .map(this::covertToProperties)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(EnvironmentVariable::getName, var -> var));
        if (!includesMap.isEmpty()) {
            properties.putAll(includesMap);
        }
    }
}
