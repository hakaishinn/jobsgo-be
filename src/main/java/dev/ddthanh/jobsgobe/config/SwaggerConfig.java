package dev.ddthanh.jobsgobe.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(getComponents())
                .info(new Info()
                        .title("Thesis OpenAPI Document")
                        .contact(new Contact()
                                .name("Duong Dinh Thanh")
                                .email("thanhteo2903@gmail.com"))
                        .description("JobsGO OpenAPI Document")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("Access Token"));
    }

    private Components getComponents() {
        SecurityScheme authorizationHeaderSchema = new SecurityScheme()
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("jwt")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER);

        return new Components()
                .securitySchemes(Map.of("Access Token", authorizationHeaderSchema));
    }
}