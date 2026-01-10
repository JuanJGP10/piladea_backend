package com.proyecto.piladea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF se desactiva en APIs REST (stateless)
            .csrf(csrf -> csrf.disable())
            
            // 2. CORS debe estar activo para que React pueda llamar a Java
            .cors(Customizer.withDefaults()) 

            // 3. Gestión de rutas (AQUÍ ESTÁ LA CLAVE)
            .authorizeHttpRequests(auth -> auth
                // A. Permitir preflight requests (necesario para React)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                // B. Rutas PÚBLICAS explícitas (Login y Registro)
                // OJO: Ya NO usamos /auth/**, sino las rutas exactas.
                .requestMatchers("/auth/login", "/auth/register").permitAll()
                
                // C. Rutas de documentación (Swagger/OpenAPI) - Opcional pero recomendado
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                // 👇👇👇 AÑADE ESTO IMPRESCINDIBLE PARA DEBUGEAR 👇👇👇
                .requestMatchers("/error").permitAll()
                
                // D. Rutas de archivos estáticos (imágenes de avatares, etc.)
                .requestMatchers("/uploads/**", "/images/**").permitAll()

                // E. Todo lo demás (incluido /auth/me) requiere TOKEN
                .anyRequest().authenticated()
            )

            // 4. Gestión de sesión: SIN ESTADO (Stateless)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 5. Providers y Filtros
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}