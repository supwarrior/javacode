package com.github.mvc.model;

import com.github.annotation.Inject;
import lombok.Data;
import lombok.ToString;

/**
 * @author 康盼Java开发工程师
 */
@Data
@ToString(callSuper=true)
@Inject
public class SuperUser extends User {
    private String address;
}
