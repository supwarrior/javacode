package com.github.env;

import com.github.env.core.EnvironmentVariable;

public enum StandardProperties implements EnvironmentVariable {

    Undefined("n/a", "Undefined Property"),
    OM_PROC_MON_WAIT_HOLD_LOGIC("0", ""),
    OM_MAX_SIZE_LOT_LIST_INQ("100", "");


    private String value;
    private boolean syncToFlag;
    private boolean syncBlockFlag;
    private boolean ready = false;
    private final String description;
    private final String defaultValue;

    StandardProperties(Object defaultValue, String description) {
        this.value = String.valueOf(defaultValue);
        this.defaultValue = String.valueOf(defaultValue);
        this.description = description;
    }


    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
//        return isReady() ? this.value : this.defaultValue;
        return this.value;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isUndefined() {
        return this == Undefined;
    }

    @Override
    public String getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public boolean syncToFlag() {
        return this.syncToFlag;
    }

    @Override
    public void makeSyncToRequired() {
        this.syncToFlag = true;
    }

    @Override
    public void makeNotSyncToRequired() {
        this.syncToFlag = false;
    }

    @Override
    public void makeSyncBlocked() {
        this.syncBlockFlag = true;
    }

    @Override
    public void makeNotSyncBlocked() {
        this.syncBlockFlag = false;
    }

    @Override
    public boolean isSyncBlocked() {
        return this.syncBlockFlag;
    }

    @Override
    public void ready() {
        this.ready = true;
    }

    @Override
    public boolean isReady() {
        return this.isUndefined() || this.ready;
    }

    @Override
    public String toString() {
        return String.format("{key:%s, value:%s}", this.getName(), this.getValue());
    }

}
