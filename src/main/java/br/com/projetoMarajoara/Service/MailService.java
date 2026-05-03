package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    //p colocar valores do app.propereties automaticamente
    @Value("${spring.mail.username}")
    private String remetente;

    public void sendMail(String destinatario, MailStructure mailStructure, String remetente) {
        validarDestinatario(destinatario);

        String remetenteFinal = (remetente == null || remetente.isBlank()) ? this.remetente : remetente;

        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom(remetenteFinal);
        sm.setTo(destinatario);
        sm.setSubject(mailStructure.getTitulo());
        sm.setText(mailStructure.getMensagem());

        try {
            mailSender.send(sm);
        } catch (MailException ex) {
            throw new IllegalStateException("Falha ao enviar e-mail via SMTP.", ex);
        }
    }

    private void validarDestinatario(String destinatario) {
        if (destinatario == null || destinatario.isBlank()) {
            throw new IllegalArgumentException("Destinatário inválido.");
        }

        try {
            InternetAddress address = new InternetAddress(destinatario);
            address.validate();
        } catch (AddressException ex) {
            throw new IllegalArgumentException("Destinatário inválido.", ex);
        }
    }
}
