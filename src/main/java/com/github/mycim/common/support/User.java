package com.github.mycim.common.support;

import com.github.jpa.lock.ObjectIdentifier;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    private static final Long serialVersionUID = 741242368963235L;
    /**
     * User ID
     */
    private ObjectIdentifier userID;
    /**
     * Password
     */
    private String password;
    /**
     * New Password
     */
    private String newPassword;
    /**
     * Function ID. For example, the Function ID of TxFutureHoldReq is "TXPC041".
     */
    private String functionID;
    /**
     * Client Node
     */
    private String clientNode;
    /**
     * Reserved for SI customization
     */
    private Object reserve;

    public User duplicate() {
        User user = new User();
        user.setUserID(userID.copy());
        user.setReserve(reserve);
        user.setNewPassword(newPassword);
        user.setPassword(password);
        user.setClientNode(clientNode);
        return user;
    }
}
