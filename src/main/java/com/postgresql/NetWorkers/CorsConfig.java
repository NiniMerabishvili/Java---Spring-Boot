package com.postgresql.NetWorkers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200, http://localhost:3333", "http://localhost:5433", "http://localhost:80")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true)
                .maxAge(3600);
    }
}
