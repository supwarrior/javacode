package com.github.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 康盼Java开发工程师
 */
public class StringUtil {


    /**
     * 类名称首字符小写
     *
     * @param clazz
     * @return
     */
    public static String getName(Class clazz) {
        char[] chars = clazz.getSimpleName().toCharArray();
        chars[0] += 32;
        return new String(chars);
    }

    /**
     * 首字母转小写
     *
     * @param name 字符串值，必须字母开头
     * @return 首字母转小写
     */
    public static String strConvertLowerCamel(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        String at = String.valueOf(name.charAt(0)).toLowerCase();
        return at + name.substring(1);
    }
}
