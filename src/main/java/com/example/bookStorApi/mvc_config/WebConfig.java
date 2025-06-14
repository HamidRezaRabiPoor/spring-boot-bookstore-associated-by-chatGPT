package com.example.bookStorApi.mvc_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/bookstore/**") // Applies to all Endpoints of api.
                .allowedOrigins("http://localhost:3000") // URL of React app
                .allowedMethods("GET","POST", "UPDATE", "PUT", "DELETE")// Allow http methods
                .allowedHeaders("*")// Allows request Headers
                .allowCredentials(true);
    }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addRedirectViewController("/books", "/login");
    }
}
