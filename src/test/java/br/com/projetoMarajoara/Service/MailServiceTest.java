package br.com.projetoMarajoara.Service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.projetoMarajoara.Model.MailStructure;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailService mailService;

    @Test
    void destinatarioInvalidoDeveSerRejeitadoAntesDoEnvio() {
        MailStructure mail = novoMail();

        assertThrows(IllegalArgumentException.class,
                () -> mailService.sendMail("email-invalido", mail, "origem@teste.com"));
    }

    @Test
    void configuracaoSmtpValidaDeveEnviarEmailDeTeste() {
        MailStructure mail = novoMail();
        ReflectionTestUtils.setField(mailService, "remetente", "sistema@marajoara.com");

        mailService.sendMail("destino@teste.com", mail, "");

        verify(mailSender).send(any(org.springframework.mail.SimpleMailMessage.class));
    }

    @Test
    void falhaSmtpDeveSerTratada() {
        MailStructure mail = novoMail();
        doThrow(new MailSendException("SMTP indisponível"))
                .when(mailSender).send(any(org.springframework.mail.SimpleMailMessage.class));

        assertThrows(IllegalStateException.class,
                () -> mailService.sendMail("destino@teste.com", mail, "origem@teste.com"));
    }

    private MailStructure novoMail() {
        MailStructure mail = new MailStructure();
        mail.setTitulo("Teste");
        mail.setMensagem("Mensagem de teste");
        return mail;
    }
}
