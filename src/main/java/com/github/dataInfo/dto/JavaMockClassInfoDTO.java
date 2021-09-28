
package com.github.dataInfo.dto;

import com.github.dataInfo.BaseCanUserType;
import lombok.Data;

/**
 * 需要mock的类信息
 *
 * @author 康盼Java开发工程师
 */
@Data
public class JavaMockClassInfoDTO extends BaseCanUserType {
    /**
     * 属性名称
     */
    private String name;
    /**
     * 父类类型
     */
    private String parentClassFullyType;
    /*
     * 需要mock的方法
     */
//    private List<JavaMockMethodInfoDTO> javaMockMethodInfoDTOList;
}
