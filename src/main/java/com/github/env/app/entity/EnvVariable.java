package com.github.env.app.entity;

import lombok.Data;

@Data
public class EnvVariable {
    private String envName;         // environment name
    private String envValue;        // environment value
}
