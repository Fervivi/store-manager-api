/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)

            // Puede lanzar excepción
            throws Exception {

        // Retorna configuración final de seguridad
        return http

                // Desactiva protección CSRF
                .csrf(csrf -> csrf.disable())

                // Configura aplicación Stateless
                .sessionManagement(session ->

                        // No crear sesiones HTTP
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configura reglas de autorización
                .authorizeHttpRequests(auth -> auth

                        // Permite acceso público
                        .requestMatchers("/api/v1/public/**")
                        .permitAll()

                        // Cualquier otro endpoint requiere autenticación
                        .anyRequest()
                        .authenticated())

                // Agrega JwtAuthenticationFilter antes del filtro estándar
                .addFilterBefore(

                        // Nuestro filtro JWT personalizado
                        jwtAuthenticationFilter,

                        // Antes del filtro default Spring
                        UsernamePasswordAuthenticationFilter.class)

                // Construye configuración final
                .build();
    }
}
