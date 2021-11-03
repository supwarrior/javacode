package com.github.jpa.lock;

import lombok.Data;

@Data
public class ObjectIdentifier {
    private String value;
    private String referenceKey;

    public ObjectIdentifier() {
    }

    public ObjectIdentifier(String value, String referenceKey) {
        this.value = value;
        this.referenceKey = referenceKey;
    }

    public ObjectIdentifier(String value) {
        this.value = value;
    }

}
