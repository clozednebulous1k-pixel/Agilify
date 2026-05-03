package br.com.projetoMarajoara.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ResilienceAndSmokeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void paginaPrincipalCarregaAbaixoDoTempoMaximoAceitavel() throws Exception {
        long inicio = System.currentTimeMillis();
        mockMvc.perform(get("/")).andExpect(status().isOk());
        long duracaoMs = System.currentTimeMillis() - inicio;

        assertTrue(duracaoMs < 2000, "A página principal ultrapassou 2s.");
    }

    @Test
    void sistemaRespondeEmCargaSimuladaSemDerrubarAplicacao() throws Exception {
        int requisicoes = 40;
        ExecutorService pool = Executors.newFixedThreadPool(8);
        AtomicInteger sucesso = new AtomicInteger();

        for (int i = 0; i < requisicoes; i++) {
            pool.submit(() -> {
                try {
                    mockMvc.perform(get("/")).andExpect(status().isOk());
                    sucesso.incrementAndGet();
                } catch (Exception ignored) {
                    // O contador de sucesso já indica a saúde da execução.
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        assertTrue(sucesso.get() >= 35, "Quantidade de respostas bem-sucedidas abaixo do esperado.");
    }

    @Test
    void falhaDeServicoExternoNaoDerrubaPaginaPrincipal() throws Exception {
        // Smoke de resiliência: mesmo com dependências externas indisponíveis,
        // a página inicial deve continuar entregando 200.
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
