package com.swoqe.librarianship.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme (
        name = "Authorization", // can be set to anything
        type = SecuritySchemeType.APIKEY,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition (
        info = @Info(title = "Sample API", version = "v1"),
        security = @SecurityRequirement(name = "Authorization") // references the name defined in the line 3
)
public class SwaggerConfiguration {

}
