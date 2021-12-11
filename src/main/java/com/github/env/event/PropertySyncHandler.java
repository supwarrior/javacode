package com.github.env.event;

import com.github.env.core.EnvironmentVariable;
import com.github.env.core.EnvironmentVariableDO;
import com.github.env.core.SwitchPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 处理环境变量值
 * 有两种方式，一是定时任务去刷，二是通过调接口去保存（都是基于事件的形式）
 * 先是通过界面去修改数据库中的值，然后定时任务去刷（有延迟）
 *
 * 思考：带来什么便利，是一种什么解决方案
 * 为啥环境变量不直接通过数据库改完之后，直接得到变量的值，定时任务去刷会有延迟
 * 为啥要定义两套，JAVA代码中定义，数据库定义
 *
 */
@Component
@Slf4j
public class PropertySyncHandler {

    private SwitchPropertySource source;

    @Autowired
    public PropertySyncHandler(SwitchPropertySource switchPropertySource) {
        this.source = switchPropertySource;
    }

    @EventListener
    public void syncTo(SyncToEvent event) {
        source.allFromLocal()
                .parallelStream()
                .filter(EnvironmentVariable::isSyncToRequired)
                .forEach(this::updateSource);
    }

    private void updateSource(EnvironmentVariable var) {
        source.refreshSource(var);
        var.makeNotSyncToRequired();
    }

    @EventListener
    public void syncFrom(SyncFromEvent event) {
        log.info("Start to sync the properties from the source data [{}]", source.getClass().getSimpleName());
        // 获取数据库中的配置变量
        List<EnvironmentVariableDO> sourceData = this.source.allFromSource();
        // 获取本地的环境变量
        List<EnvironmentVariable> vars = source.allFromLocal();
        vars.stream().filter(var -> !var.isReady()).forEach(EnvironmentVariable::ready);

        // update all values that have changed and are required to be changed from the source
        List<EnvironmentVariableDO> list = sourceData.stream()
                .filter(data -> StringUtils.isNotEmpty(data.getEnvironmentVariableValue()))
                .filter(this::syncFromRequired).collect(Collectors.toList());
        list.forEach(this::updateValue);

        // if the property is not found in the source, reset the value to default
        Set<String> nameSet = sourceData.stream()
                .map(EnvironmentVariableDO::getEnvironmentVariableID)
                .collect(Collectors.toSet());
        vars.stream()
                .filter(this::syncFromRequired)
                .filter(var -> !nameSet.contains(var.getName()))
                .forEach(EnvironmentVariable::resetValue);
    }

    private boolean syncFromRequired(EnvironmentVariableDO data) {
//        EnvironmentVariable var = source.find(data.getEnvironmentVariableID());
//        return syncFromRequired(var) && !var.getValue().equals(data.getEnvironmentVariableValue());
        return true;
    }
    private boolean syncFromRequired(EnvironmentVariable var) {
        return !var.isUndefined() && !var.isSyncBlocked();
    }

    private void updateValue(EnvironmentVariableDO data) {
        EnvironmentVariable environmentVariable = source.find(data.getEnvironmentVariableID());
        log.info("environmentVariable {}",environmentVariable);
        environmentVariable.setValue(data.getEnvironmentVariableValue());
    }

}
