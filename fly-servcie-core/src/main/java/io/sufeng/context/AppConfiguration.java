package io.sufeng.context;

import io.sufeng.common.file.aliyun.AliyunOssObjectManagerFactory;
import io.sufeng.common.icon.github.GitHubIconServiceProvider;
import io.sufeng.common.icon.group.GroupIconServiceProvider;
import io.sufeng.common.icon.user.UserIconServiceProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

/**
 * @Author zhangchao
 * @Date 2019/6/21 10:11
 * @Version v1.0
 */
@ComponentScan({"io.sufeng.context","io.sufeng.imimpl"})
@EnableJpaRepositories
@EntityScan("io.sufeng.context.domain.entity")
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
    @Bean
    public GitHubIconServiceProvider getGitHubIconServiceProvider(){
        return new GitHubIconServiceProvider();
    }




    @PostConstruct
    public void run(){
        System.out.println("===============  run  ===================================================>");
        System.out.println("===============  run  ===================================================>");
        System.out.println("===============  run  ===================================================>");
        System.out.println("===============  run  ===================================================>");
        System.out.println("===============  run  ===================================================>");
        System.out.println("===============  run  ===================================================>");
    }
}
