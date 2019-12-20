package org.flyants.oauth2.clientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author zhangchao
 * @Date 2019/4/28 14:42
 * @Version v1.0
 */
@EnableTransactionManagement
@SpringBootApplication
public class FlyantsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyantsClientApplication.class, args);
    }

}
