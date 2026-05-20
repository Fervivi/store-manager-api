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

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/v1/public/**", "/v3/api-docs/**", "/swagger-ui/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
