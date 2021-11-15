package com.github.postProcessEvent.model;

public enum TaskStatus {
    /**
     * the task is waiting for executing
     */
    Waiting,

    /**
     * the task is reserved for executing
     */
    Reserved,

    /**
     * the task has been already executed
     */
    Completed,

    /**
     * the task has been skipped
     */
    Skipped,

    /**
     * the task has been deleted
     */
    Deleted,

    /**
     * the task has been executed with error
     */
    Error,

    /**
     * the task has been paused and await to be resumed
     */
    Paused,

}
