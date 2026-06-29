/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfigProduct {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientProduct(@Value("${app.service.products.base-url}") String baseUrl) {
        return resolveBuilder(baseUrl)
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    private WebClient.Builder resolveBuilder(String url) {
        return isLocalURL(url) ? WebClient.builder() : loadBalancedWebClientBuilder();
    }

    private boolean isLocalURL(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }
        try {
            String host = URI.create(url).getHost();
            return host != null
                    && (!url.startsWith("lb://"))
                    && (host.equalsIgnoreCase("localhost")
                            || host.equals("127.0.0.1")
                            || host.equals("[::1]")
                            || host.equals("0:0:0:0:0:0:0:1"));
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("Bad domain service url: " + url);
        }
    }
}
