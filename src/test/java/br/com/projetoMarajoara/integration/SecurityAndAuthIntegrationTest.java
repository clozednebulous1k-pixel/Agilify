package br.com.projetoMarajoara.integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAndAuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void usuarioSemPapelAdmNaoAcessaRotasAdministrativas() throws Exception {
        mockMvc.perform(get("/adm/eventos").with(user("morador@teste.com").roles("MORADOR")))
                .andExpect(status().isForbidden());
    }

    @Test
    void tentativasRepetidasNaoAutorizadasSaoTratadasSemQueda() throws Exception {
        for (int i = 0; i < 5; i++) {
            mockMvc.perform(get("/adm/configuracoes"))
                    .andExpect(status().is3xxRedirection());
        }
    }

    @Test
    void loginErradoExibeMensagemDeCredenciaisInvalidas() throws Exception {
        mockMvc.perform(formLogin("/login").user("email", "naoexiste@teste.com").password("senha", "errada"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?error"));

        mockMvc.perform(get("/").param("error", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Usuário ou senha inválidos")));
    }

    @Test
    void recuperacaoSenhaEnvioCodigoNaoExigeLogin() throws Exception {
        mockMvc.perform(post("/emailSender/sendCodMor")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("titulo", "Código troca de senha")
                .param("mensagem", "teste")
                .param("email", "naoexiste@example.com"))
                .andExpect(result -> Assertions.assertNotEquals(302, result.getResponse().getStatus(),
                        "POST público não deve redirecionar para login (confira path em SecurityConfig)"));
    }

    @Test
    void dadosDeMoradorNaoSaoExpostosEmRespostaInadequada() throws Exception {
        mockMvc.perform(get("/morador/perfil"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/morador/perfil").with(user("morador@teste.com").roles("MORADOR")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }
}
