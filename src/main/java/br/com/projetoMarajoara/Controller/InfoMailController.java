package br.com.projetoMarajoara.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projetoMarajoara.Model.MailStructure;
import br.com.projetoMarajoara.Service.MailService;

@Controller
@RequestMapping("/emailSender")
public class InfoMailController {

	@Autowired
	MailService ms;

	@PostMapping("/sendAdm")
	public String sendMailAdm(@RequestParam String titulo, @RequestParam String mensagem, 
			@RequestParam String email, @RequestParam String remetente  ) {
		MailStructure mail = new MailStructure();
		mail.setTitulo(titulo);
		mail.setMensagem(mensagem);
		ms.sendMail(email, mail, remetente);
		return "redirect:/adm/reclamacoes";
	}
	
	@PostMapping("/sendMor")
	public String sendMailMor(@RequestParam String titulo, @RequestParam String mensagem, 
			@RequestParam String email, @RequestParam String remetente  ) {
		MailStructure mail = new MailStructure();
		mail.setTitulo(titulo);
		mail.setMensagem(mensagem);
		ms.sendMail(email, mail, remetente);
		return "redirect:/morador/reclamacoes";
	}
	
	@PostMapping("/sendCodMor")
	public ResponseEntity<String> sendCodMailMor(@RequestParam String titulo, @RequestParam String mensagem,
			@RequestParam String email) {
		MailStructure mail = new MailStructure();
		mail.setTitulo(titulo);
		mail.setMensagem(mensagem);
		try {
			// Gmail exige que o From seja a conta autenticada (spring.mail.username).
			ms.sendMail(email, mail, "");
			return ResponseEntity.ok("Código enviado!");
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		} catch (IllegalStateException ex) {
			String detalhe = causaRaizBreve(ex);
			return ResponseEntity.internalServerError().body(
					"Não foi possível enviar o e-mail. Verifique usuário/senha SMTP (Senha de app do Gmail) e conexão."
							+ (detalhe.isEmpty() ? "" : " Detalhe: " + detalhe));
		}
	}

	private static String causaRaizBreve(Throwable ex) {
		Throwable cur = ex;
		while (cur != null && cur.getCause() != null && cur != cur.getCause()) {
			cur = cur.getCause();
		}
		if (cur == null || cur.getMessage() == null) {
			return "";
		}
		String msg = cur.getMessage().replace('\r', ' ').replace('\n', ' ').trim();
		return msg.length() > 280 ? msg.substring(0, 277) + "..." : msg;
	}

}
