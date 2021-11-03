package com.github.jpa.lock;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "default.lock", ignoreUnknownFields = false)
@Component
public class Properties {
    private String mode = "JPA";
    private int retry = 5;
    private long timeout = 60L;

    public Properties() {
    }

    public String getMode() {
        return this.mode;
    }

    public int getRetry() {
        return this.retry;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public void setRetry(final int retry) {
        this.retry = retry;
    }

    public void setTimeout(final long timeout) {
        this.timeout = timeout;
    }
}
