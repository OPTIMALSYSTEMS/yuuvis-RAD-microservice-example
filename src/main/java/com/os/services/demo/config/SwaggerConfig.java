package com.os.services.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@Configuration
public class SwaggerConfig {
    public static final String ApiTitle = "DEMO API";

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("demo-api").pathsToMatch(paths()).addOpenApiCustomiser(openApi -> {
            openApi.addSecurityItem(new SecurityRequirement().addList("jwtAuth"));

            openApi.getComponents().addSecuritySchemes("jwtAuth", new SecurityScheme().name(HttpHeaders.AUTHORIZATION)
                    .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).description("JWT"));
        }).build();

    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title(ApiTitle).description("").version("3.0").contact(new Contact().name("OPTIMAL SYSTEMS GmbH")
                        .email("contact@optimal-systems.com").url("http://www.optimal-systems.com"))
                .termsOfService("http://www.optimal-systems.com"));
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder().group("administration-api").pathsToMatch(adminApiPaths())
                .addOpenApiCustomiser(openApi -> {
                    openApi.addSecurityItem(new SecurityRequirement().addList("jwtAuth"));

                    openApi.getComponents().addSecuritySchemes("jwtAuth",
                            new SecurityScheme().name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.APIKEY)
                                    .in(SecurityScheme.In.HEADER).description("JWT"));
                }).build();
    }

    private String[] paths() {
        return new String[]{"/api/dms/**"};
    }

    private String[] adminApiPaths() {
        return new String[]{"/manage/**"};
    }
}
