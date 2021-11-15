package com.github.postProcessEvent.model;

public enum DispatchReadinessState {

    /**
     * the lot is ready to be dispatched
     */
    Ready,

    /**
     * some errors occurred before the lot is ready to be dispatched
     */
    Error,

    /**
     * the lot is currently executing in joined phase
     */
    Pending_JoinedTasks,

    /**
     * the lot is currently executing in post phase
     */
    Pending_PostTasks
}
