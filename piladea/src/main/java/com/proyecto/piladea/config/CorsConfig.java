package com.proyecto.piladea.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    // HEMOS CAMBIADO EL NOMBRE DEL MÉTODO AQUÍ
    // De 'corsFilter' a 'corsFilterRegistrationBean'
    // Esto evita el choque con Spring Security
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 1. Permitir credenciales
        config.setAllowCredentials(true);

        // 2. Orígenes permitidos
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:8081",
                "https://localhost:8081", // 🔴 ESTA FALTABA
                "http://127.0.0.1:8081",
                "https://127.0.0.1:8081",
                "http://localhost:5173",
                "http://localhost:3000"));

        // 3. Headers y Métodos
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        source.registerCorsConfiguration("/**", config);

        // Crear el Bean de Registro con prioridad máxima
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}