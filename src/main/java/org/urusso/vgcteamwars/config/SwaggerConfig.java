package org.urusso.vgcteamwars.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private final String version;
    private final String title;
    private final String description;

    public SwaggerConfig(@Value("${swagger.version}") String version, @Value("${swagger.title}") String title,
                         @Value("${swagger.description}") String description) {
        this.title = title;
        this.version = version;
        this.description = description;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(bearerSecurityScheme());
    }

    private io.swagger.v3.oas.models.Components bearerSecurityScheme() {
        return new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }
}
