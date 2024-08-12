package com.tobi.booker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Olutobi",
                        email= "olutobi@gmail.com",
                        url = "https://null"
                ),
                description = "OpenAPI Documentation for Booker",
                title = "OpenAPI Specification",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Development Server",
                        url = "http://localhost:5000/api/v1"
                )
        },
        security = {
                @SecurityRequirement(name="bearerAuth")
        }
)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
