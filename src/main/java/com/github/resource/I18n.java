package com.github.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Slf4j
public class I18n {

    public I18n() {

    }

    /**
     * spring resources
     */
    private static final ReloadableResourceBundleMessageSource MESSAGE_SOURCE =
            new ReloadableResourceBundleMessageSource();

    static {
        // 创建文件的规则 message_**_**.properties 请根据国际化文件创建
        // 指定国家化资源文件路径
        MESSAGE_SOURCE.setBasename("i18n/message");
        // 指定将用来加载对应资源文件时使用的编码，默认为空，表示将使用默认的编码进行获取。
        MESSAGE_SOURCE.setDefaultEncoding("UTF-8");
        // 是否允许并发刷新
        MESSAGE_SOURCE.setConcurrentRefresh(true);
        // ReloadableResourceBundleMessageSource也是支持缓存对应的资源文件的，默认的缓存时间为永久，
        // 即获取了一次资源文件后就将其缓存起来，以后再也不重新去获取该文件。这个可以通过setCacheSeconds()方法来指定对应的缓存时间，单位为秒
        MESSAGE_SOURCE.setCacheSeconds(1200);
    }


    /**
     * description:获取中文的数据
     *
     * @param key
     * @return
     */
    public static String getChineseValueByKey(String key) {
        try {
            return MESSAGE_SOURCE.getMessage(key, null, Locale.CHINA);
        } catch (NoSuchMessageException e) {
            log.error("getChineseValueByKey()->error: {}", e.getMessage());
            return "";
        }
    }

    public static String getEnvValueByKey(String key) {
        try {
            return MESSAGE_SOURCE.getMessage(key, null, Locale.UK);
        } catch (NoSuchMessageException e) {
            log.error("getValueByEnv()->error: {}", e.getMessage());
            return "";
        }
    }

    public static String getLocaleByKey(String key, Locale locale) {
        return MESSAGE_SOURCE.getMessage(key, null, locale);
    }


}
