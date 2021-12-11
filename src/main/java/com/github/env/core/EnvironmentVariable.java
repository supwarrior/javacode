package com.github.env.core;

import com.github.ddd.businessObject.BaseBO;

public interface EnvironmentVariable extends BaseBO {
    void setValue(String value);

    String getValue();

    @Deprecated
    default void setName(String name) {
    }

    String getName();

    @Deprecated
    default void setDescription(String description) {
    }

    String getDescription();

    default void resetValue() {
        this.setValue(this.getDefaultValue());
    }

    default boolean isTrue() {
        return !("false".equals(this.getValue()) || "0".equals(this.getValue()));
    }

    default boolean isUndefined() {
        return false;
    }

    default String getDefaultValue() {
        return "";
    }

    default boolean isSyncToRequired() {
        return syncToFlag();
    }

    default boolean syncToFlag() {
        return false;
    }

    default void makeSyncToRequired() {
    }

    default void makeNotSyncToRequired() {
    }

    default void makeSyncBlocked() {
    }

    default void makeNotSyncBlocked() {
    }

    default boolean isSyncBlocked() {
        return false;
    }

    default void ready() {
    }

    boolean isReady();


}
