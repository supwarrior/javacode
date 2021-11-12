package com.github.ddd.jpa.esec;

import com.github.esec.entity.User;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BasicParam {
    @NotNull(message = "user Can't be null")
    @Valid
    private User user;

}
