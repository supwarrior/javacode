package com.github.mvc.model.oms;

import lombok.Data;

/**
 * 晶舟
 *
 * @author 康盼Java开发工程师
 */
@Data
public class Carrier {
    private String carrierId;
    private String description;
    private String carrierStatus;
    private String carrierCategory;
    private String empty;
}
