package org.flyants.authorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class FlyantsAuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyantsAuthorizeApplication.class, args);
    }

}
