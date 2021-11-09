package com.github.ddd.jpa;

import java.lang.reflect.Field;

public abstract class Content {
    protected Field field;

    public Content() {
    }

    public Content(Field field) {
        this.field = field;
    }


}
