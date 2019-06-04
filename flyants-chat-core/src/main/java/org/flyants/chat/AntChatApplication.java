package org.flyants.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class AntChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntChatApplication.class, args);
    }

}
