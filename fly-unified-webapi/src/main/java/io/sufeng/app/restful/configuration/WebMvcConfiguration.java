package io.sufeng.app.restful.configuration;

import io.sufeng.app.restful.app.AppVersion;
import io.sufeng.imimpl.netty.config.Config;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns(AppVersion.version + "/**");    // 拦截所有请求，通过判断是否有 @Anonymous 注解 决定是否需要登录
    }

}