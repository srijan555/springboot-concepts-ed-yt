package com.ed.springboot.cricketteam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI cricketTeamOpenAPI() {
        Info info = new Info()
                .title("Cricket Team Management API")
                .description("API documentation for Cricket Team Management application")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Srijan")
                        .email("xyz@example.com")
                        .url("https://www.example.com"));
        return new OpenAPI().info(info);
    }
}
