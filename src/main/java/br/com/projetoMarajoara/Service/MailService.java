package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    //p colocar valores do app.propereties automaticamente
    @Value("${spring.mail.username}")
    private String remetente;

    public void sendMail(String destinatario, MailStructure mailStructure, String remetente) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom(remetente);
        sm.setTo(destinatario);
        sm.setSubject(mailStructure.getTitulo());
        sm.setText(mailStructure.getMensagem());

        mailSender.send(sm);
    }
}
