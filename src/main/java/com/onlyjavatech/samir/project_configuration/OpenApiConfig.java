package com.onlyjavatech.samir.project_configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("EmployeeDepartment API1").version("2.6.4").description("Employee Department Api Openapi implementation1"));
    }
}
