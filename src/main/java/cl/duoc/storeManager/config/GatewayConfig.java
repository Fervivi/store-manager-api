/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.config;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Slf4j
@Configuration
public class GatewayConfig {

    @Value("${app.service.carts.base-url}")
    private String cartsBaseUrl;

    @Value("${app.service.products.base-url}")
    private String productsBaseUrl;

    @Bean
    public RouterFunction<ServerResponse> domainRoutes() {
        return route("carts_passthrough")
                .route(path("/api/v1/carts/**"), http())
                .filter(getRoutingFilter(cartsBaseUrl))
                .build()
                .and(route("products_passthrough")
                        .route(path("/api/v1/products/**"), http())
                        .filter(getRoutingFilter(productsBaseUrl))
                        .build())
                .and(route("carts_health")
                        .route(path("/api/v1/health"), http())
                        .filter(getRoutingFilter(cartsBaseUrl))
                        .build())
                .and(route("carts_docs")
                        .route(path("/carts/v3/api-docs"), http())
                        .before(rewritePath("/carts/(?<segment>.*)", "/${segment}"))
                        .filter(getRoutingFilter(cartsBaseUrl))
                        .build())
                .and(route("products_docs")
                        .route(path("/products/v3/api-docs"), http())
                        .before(rewritePath("/products/(?<segment>.*)", "/${segment}"))
                        .filter(getRoutingFilter(productsBaseUrl))
                        .build());
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> getRoutingFilter(String url) {
        log.info("Routing URL: '{}'", url);
        return isLocalURL(url) ? uri(url) : lb(URI.create(url).getHost());
    }

    private boolean isLocalURL(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Missing domain service url");
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
