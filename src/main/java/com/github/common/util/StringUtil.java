package com.github.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

import static com.github.common.cons.Constant.OS;
import static com.github.common.cons.Constant.WINDOWS;

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

    /**
     * 添加前后分隔符
     *
     * @param configPath 路径
     * @return 返回添加前后路径分隔符的字符串
     */
    public static String addSeparator(String configPath) {
        String os = System.getProperty(OS);

        if(!os.toLowerCase().contains(WINDOWS.toLowerCase())){
            // 只有在非windows才添加前路径分隔符
            if (!configPath.startsWith(File.separator)) {
                configPath = File.separator + configPath;
            }
        }

        if (!configPath.endsWith(File.separator)) {
            configPath = configPath + File.separator;
        }
        return configPath;
    }
}
