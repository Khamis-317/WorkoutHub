package com.WorkoutHub.workout_hub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mohamed Khamis",
                        email = "mohamedkhamis20045@gmail.com"
                ),
                description = "OpenAPI documentation for WorkoutHub Backend",
                title = "WorkoutHub API",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    /**
     * This bean forces Swagger to use the Spring Boot configured ObjectMapper.
     * Without this, Swagger uses its own default mapper which ignores
     * spring.jackson.property-naming-strategy=SNAKE_CASE
     */
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }
}