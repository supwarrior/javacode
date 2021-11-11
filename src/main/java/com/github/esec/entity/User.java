package com.github.esec.entity;

import com.github.jpa.lock.ObjectIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class User implements Serializable {
    private static final Long serialVersionUID = 741242368963235L;
    /**
     * User ID
     */
    @NotNull(message = "user id Can't be null")
    private ObjectIdentifier userID;
    /**
     * Password
     */
    @NotBlank(message = "password Can't be empty")
    private String password;
    /**
     * Function ID. For example, the Function ID of TxFutureHoldReq is "TXPC041".
     */
    @NotBlank(message = "functionID Can't be empty")
    private String functionID;

    public User() {
        this.userID = ObjectIdentifier.build("ADMIN", "ADMIN");
    }
}