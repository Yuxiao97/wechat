package com.yuxiao.wechat.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * Ehcache初始化
 */
@Configuration
@EnableCaching
@Slf4j
public class EhcacheManagerConfig {

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        log.info("init ehcache...");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        log.info("ehcache init finished.");
        return cacheManagerFactoryBean;
    }


}
