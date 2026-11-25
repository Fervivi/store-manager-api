/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.storeManager.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.duoc.storeManager.dto.response.ProductResponseDto;
import cl.duoc.storeManager.service.StoreManagerService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityConfigTest {

    private static final String VALIDATE_PATH = "/api/v1/store-manager/products/1/validate";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreManagerService storeManagerService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    private String tokenWithRole(String role) {
        return "Bearer "
                + JWT.create()
                        .withIssuer(issuer)
                        .withSubject("tester@duocuc.cl")
                        .withClaim("roles", List.of(role))
                        .withExpiresAt(Instant.now().plusSeconds(300))
                        .sign(Algorithm.HMAC256(secret));
    }

    @Test
    void health_withoutToken_returns200() throws Exception {
        mockMvc.perform(get("/api/v1/store-manager/health")).andExpect(status().isOk());
    }

    @Test
    void validateProduct_withoutToken_returns401() throws Exception {
        mockMvc.perform(get(VALIDATE_PATH)).andExpect(status().isUnauthorized());
    }

    @Test
    void validateProduct_withInvalidToken_returns401() throws Exception {
        mockMvc.perform(get(VALIDATE_PATH).header(HttpHeaders.AUTHORIZATION, "Bearer token-invalido"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void validateProduct_asCustomer_returns200() throws Exception {
        when(storeManagerService.validateProduct(anyLong(), anyString())).thenReturn(new ProductResponseDto());

        mockMvc.perform(get(VALIDATE_PATH).header(HttpHeaders.AUTHORIZATION, tokenWithRole("user")))
                .andExpect(status().isOk());
    }

    @Test
    void productsPassthrough_withoutToken_isNotRejectedBySecurity() throws Exception {
        int status = mockMvc.perform(get("/api/v1/products/1"))
                .andReturn()
                .getResponse()
                .getStatus();

        assertThat(status).isNotIn(401, 403);
    }

    @Test
    void cartsPassthrough_withoutToken_returns401() throws Exception {
        mockMvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}
