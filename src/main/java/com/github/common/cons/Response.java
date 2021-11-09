package com.github.common.cons;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@JSONType(ignores = {"serialVersionUID", "FORMAT_SPLIT"})
public class Response implements Serializable  {

    public static Response createSucc(final String transactionID, final Object body) {
        return new Response(Code.SUCCESS_CODE, transactionID, "Success", body);
    }

    private Integer code;
    private String transactionID;
    private String message;
    private Object body = null;

    public Response(Integer code, String transactionID, String message, Object body) {
        this.code = code;
        this.transactionID = transactionID;
        this.message = message;
        this.body = body;
    }

    public static class  Code {
        public static final int SUCCESS_CODE = 0;
    }
}
