/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

class ProductClientTest {

    private static ProductClient clientRespondingWith(HttpStatus status, String body) {
        WebClient webClient = WebClient.builder()
                .exchangeFunction(request -> Mono.just(ClientResponse.create(status)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(body)
                        .build()))
                .build();
        return new ProductClient(webClient);
    }

    @Test
    void getProductById_whenProductApiReturns200_returnsProduct() {
        ProductClient client = clientRespondingWith(HttpStatus.OK, """
                {"id":1,"nombre":"Pelota","stock":3,"estado":"Activo"}
                """);

        ProductResponseDto product = client.getProductById(1L, "Bearer token");

        assertThat(product.getNombre()).isEqualTo("Pelota");
        assertThat(product.getEstado()).isEqualTo("Activo");
    }

    @Test
    void getProductById_whenProductApiReturns404_throwsResourceNotFound() {
        ProductClient client = clientRespondingWith(HttpStatus.NOT_FOUND, "{}");

        assertThatThrownBy(() -> client.getProductById(99L, "Bearer token"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Producto no encontrado con ID: 99");
    }

    @Test
    void getProductById_whenProductApiFails_propagatesError() {
        ProductClient client = clientRespondingWith(HttpStatus.INTERNAL_SERVER_ERROR, "{}");

        assertThatThrownBy(() -> client.getProductById(1L, "Bearer token"))
                .isInstanceOf(WebClientResponseException.class);
    }
}
