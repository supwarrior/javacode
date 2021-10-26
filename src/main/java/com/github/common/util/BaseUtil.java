package com.github.common.util;

import java.math.BigDecimal;

/**
 * @author 康盼Java开发工程师
 */
public class BaseUtil {

    public static Integer convertI(Object obj) {
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).intValue();
        }
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? 1 : 0;
        }
        return Double.valueOf(obj.toString()).intValue();
    }
}