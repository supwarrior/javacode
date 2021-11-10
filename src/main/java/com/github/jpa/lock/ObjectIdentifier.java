package com.github.jpa.lock;

import com.alibaba.cloud.commons.lang.StringUtils;
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

    public ObjectIdentifier copy() {
        return build(this.value, this.referenceKey);
    }

    public static ObjectIdentifier build(String value, String referenceKey) {
        return StringUtils.isEmpty(value) && StringUtils.isEmpty(referenceKey) ? emptyIdentifier() : new ObjectIdentifier(value, referenceKey);
    }

    public static ObjectIdentifier emptyIdentifier() {
        return new ObjectIdentifier("", "");
    }


}
