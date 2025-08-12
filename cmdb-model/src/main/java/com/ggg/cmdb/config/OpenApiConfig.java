package com.ggg.cmdb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI configuration.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Defines the OpenAPI metadata for the CMDB services.
     *
     * @return configured {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI cmdbOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CMDB API")
                        .version("v1")
                        .description("CMDB service API documentation"));
    }
}
