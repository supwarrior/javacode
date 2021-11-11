package com.github.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    /**
     * yyyy
     */
    public static final String PATTEN_DATE_FORMAT_Y = "yyyy";
    /**
     * yyyyMMdd
     */
    public static final String PATTEN_DATE_FORMAT_YMD = "yyyyMMdd";
    /**
     * HHmm
     */
    public static final String PATTEN_DATE_FORMAT_HM = "HHmm";
    /**
     * HHmmss
     */
    public static final String PATTEN_DATE_FORMAT_HMS = "HHmmss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String PATTEN_DATE_FORMAT_YMDHMS_NONE = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String PATTEN_DATE_FORMAT_YMDHMS_MIN_NONE = "yyyyMMddHHmmssSSS";
    /**
     * yyyy-MM-dd
     */
    public static final String PATTEN_DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd
     */
    public static final String PATTEN_DATE_FORMAT_SLASH = "yyyy/MM/dd";
    /**
     * yyyyMMdd
     **/
    public static final String PATTEN_DATE_FORMAT_MAC = "yyyyMMdd";
    /**
     * yyyy-MM-dd HH:mm:ss
     **/
    public static final String PATTEN_DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String PATTEN_DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTEN_DATE_FORMAT_YMDHMS_NORMAL = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss:SSS
     */
    public static final String PATTEN_DATE_FORMAT_YMDHMSS = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * description:获取指定指定格式的时间字符串
     *
     * @param date   指定时间
     * @param format 时间格式
     * @return java.lang.String 时间字符串
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * description:获取指定指定格式的时间字符串
     *
     * @param date   指定时间
     * @param format 时间格式
     * @return java.lang.String 时间字符串
     */
    public static Date stringToDate(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}