package br.com.projetoMarajoara.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.projetoMarajoara.Model.Reserva;
import br.com.projetoMarajoara.Service.ReservaService;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @Test
    void fluxoCompletoDeReservaComSucesso() throws Exception {
        when(reservaService.isPeriodoReservado(eq("salão"), any(LocalDate.class), any(Reserva.class))).thenReturn(false);
        doNothing().when(reservaService).save(any(Reserva.class));

        mockMvc.perform(post("/addReserva")
                        .with(user("morador@teste.com").roles("MORADOR"))
                        .param("espaco", "salão")
                        .param("data", "2026-05-10")
                        .param("manha", "true")
                        .param("tarde", "false")
                        .param("noite", "false")
                        .param("reservado_por", "morador@teste.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/morador/aluguel"))
                .andExpect(flash().attribute("sucesso", "Reserva realizada com sucesso!"));
    }

    @Test
    void listaDeHistoricoDeReservasRetornaVazioDeFormaConsistente() throws Exception {
        when(reservaService.getAllReservas()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/adm/aluguel").with(user("admin@teste.com").roles("ADM")))
                .andExpect(status().isOk());
    }

    @Test
    void listaDeDisponibilidadeCoerenteComReservasExistentesQuandoConflito() throws Exception {
        when(reservaService.isPeriodoReservado(eq("quadra"), any(LocalDate.class), any(Reserva.class))).thenReturn(true);

        mockMvc.perform(post("/addReserva")
                        .with(user("morador@teste.com").roles("MORADOR"))
                        .param("espaco", "quadra")
                        .param("data", "2026-05-10")
                        .param("manha", "true")
                        .param("tarde", "false")
                        .param("noite", "false")
                        .param("reservado_por", "morador@teste.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/morador/aluguel"))
                .andExpect(flash().attribute("erro",
                        "Este período já está reservado. Escolha outro período ou outro dia."));
    }
}
