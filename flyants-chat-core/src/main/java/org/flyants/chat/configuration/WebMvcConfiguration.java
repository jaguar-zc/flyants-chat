package org.flyants.chat.configuration;

import org.flyants.chat.web.v1.app.AppVersion;
import org.flyants.chat.web.v1.platform.PlatformVersion;
import org.flyants.common.file.aliyun.AliyunOssObjectManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author zhangchao
 * @Date 2019/5/17 11:29
 * @Version v1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
//
//    @Autowired
//    private DuomiProperties duomiProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns(PlatformVersion.version + "/**");    // 拦截所有请求，通过判断是否有 @Anonymous 注解 决定是否需要登录
        registry.addInterceptor(authenticationInterceptor).addPathPatterns(AppVersion.version + "/**");    // 拦截所有请求，通过判断是否有 @Anonymous 注解 决定是否需要登录
    }




}