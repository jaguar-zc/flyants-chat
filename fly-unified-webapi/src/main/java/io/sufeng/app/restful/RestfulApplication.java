package io.sufeng.app.restful;

import io.sufeng.context.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: sufeng
 * @create: 2019-11-29 10:45
 */
@ImportAutoConfiguration(AppConfiguration.class)
@SpringBootApplication
public class RestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }

}
