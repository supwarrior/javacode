package com.github.common.cons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 康盼Java开发工程师
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Code {
    private int code;
    private String message;

    public Code(String temp) {
        if (null == temp) {
            return;
        }
        int splitIndex = temp.indexOf(",");
        this.code = Integer.parseInt(temp.substring(1, splitIndex).trim());
        int index = temp.indexOf("\"");

        final int notFound = -1;
        if (notFound == index) {
            this.message = temp.substring(splitIndex + 1, temp.length() - 1).trim();
        } else {
            this.message = temp.substring(index + 1, temp.lastIndexOf("\"")).trim();
        }
    }

}