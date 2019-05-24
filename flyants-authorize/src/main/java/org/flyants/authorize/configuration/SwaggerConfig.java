package org.flyants.authorize.configuration;

import org.flyants.authorize.web.v1.platform.PlatformVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zhangchao
 * @Date 2019/5/24 16:25
 * @Version v1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket useApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.flyants.authorize.web.v1.platform"))
                .paths(path -> path.startsWith(PlatformVersion.version))
                .build();
    }

}
