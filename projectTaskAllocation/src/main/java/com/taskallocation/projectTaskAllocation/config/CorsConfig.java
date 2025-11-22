package com.taskallocation.projectTaskAllocation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:4200") // Use allowedOriginPatterns for future flexibility
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Accept all headers (simplifies CSRF + custom headers)
                .exposedHeaders("X-CSRF-TOKEN", "X-XSRF-TOKEN") // Let frontend read these if needed
                .allowCredentials(true)
                .maxAge(3600); // Cache pre-flight response for 1 hour
    }
}
