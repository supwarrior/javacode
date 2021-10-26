package com.github.code;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
@EnableCaching
@Slf4j
public class EHCacheConfig {
    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        // C:\Users\AppData\Local\Temp\
        log.info("cache temp file:{}",System.getProperty("java.io.tmpdir"));
        CacheManager myCache = CacheManager.getCacheManager("myCache");
        if (myCache == null){
            myCache = CacheManager.create();
        }
        return new EhCacheCacheManager(myCache);
    }
}