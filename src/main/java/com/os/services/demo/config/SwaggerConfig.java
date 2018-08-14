
package com.os.services.demo.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
// Enable swagger 2.0 spec
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(" demo-api")
            .apiInfo(apiInfo())
            .select()
                .apis(RequestHandlerSelectors.basePackage("com.os.services.demo"))
                .paths(apiPaths())
                .build()
        ;
        // @formatter:on
    }

    @Bean
    public Docket adminApi()
    {
        // @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("administation-api")
            .apiInfo(apiInfo())
            .select()
                .apis(RequestHandlerSelectors.any())
                .paths(adminApiPaths())
                .build()
        ;
        // @formatter:on
    }

    // @see:
    // https://github.com/springfox/springfox-demos/blob/master/boot-swagger/src/main/java/springfoxdemo/boot/swagger/Application.java
    private ApiInfo apiInfo()
    {
        // @formatter:off
        return new ApiInfoBuilder()
                .title("DEMO API")
                .description("demo service.")
                .termsOfServiceUrl("http://www.optimal-systems.de")                
                .version("2.0")
                .build();
        // @formatter:on
    }

    @Bean
    UiConfiguration uiConfig()
    {
        return new UiConfiguration("validatorUrl");
    }

    private Predicate<String> apiPaths()
    {
        // @formatter:off
        return or(
                  regex("/api.*"),
                  regex("/test.*"));   
        // @formatter:on 
    }

    private Predicate<String> adminApiPaths()
    {
        // @formatter:off
        return regex("/manage.*");   
        // @formatter:on 
    }
}
