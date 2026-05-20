/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfigCart {

    @Bean
    public WebClient webClientCart() {
        return WebClient.builder()
                .baseUrl("http://localhost:8084")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
