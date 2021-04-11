package com.br.brasilprev.customers.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")))
                .info(new Info().title("Customer/Address/actuator API").version(appVersion)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        String[] paths = { "/api/user/**" };
        return GroupedOpenApi.builder().group("users").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi actuatorOpenApi() {
        String[] paths = { "/actuator/**" };
        String[] packagedToMatch = { "com.br.brasilprev.customers.config" };
        return GroupedOpenApi.builder().group("actuator")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)
                .build();
    }

    @Bean
    public GroupedOpenApi addressOpenApi() {
        String[] paths = { "/api/address/**" };
        String[] packagedToMatch = { "com.br.brasilprev.customers.entrypoint.facade.rest" };
        return GroupedOpenApi.builder().group("address")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)
                .build();
    }
}
