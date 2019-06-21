package org.flyants.chat.configuration;

import org.flyants.common.file.aliyun.AliyunOssObjectManagerFactory;
import org.flyants.common.icon.group.GroupIconServiceProvider;
import org.flyants.common.icon.user.UserIconServiceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangchao
 * @Date 2019/6/21 10:11
 * @Version v1.0
 */
@Configuration
public class AppConfiguration {

    @Bean
    public AliyunOssObjectManagerFactory getAliyunOssObjectManagerFactory(){
        return new AliyunOssObjectManagerFactory();
    }

    @Bean
    public UserIconServiceProvider getUserIconServiceProvider(){
        return new UserIconServiceProvider();
    }

    @Bean
    public GroupIconServiceProvider getGroupIconServiceProvider(){
        return new GroupIconServiceProvider();
    }
}
