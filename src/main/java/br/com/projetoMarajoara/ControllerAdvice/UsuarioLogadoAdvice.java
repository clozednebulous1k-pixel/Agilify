package br.com.projetoMarajoara.ControllerAdvice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import br.com.projetoMarajoara.Model.Morador;
import br.com.projetoMarajoara.Repository.UsuarioLogado;

@ControllerAdvice
public class UsuarioLogadoAdvice {

    @ModelAttribute
    public void adicionarUsuarioLogado(Model model, Authentication authentication) {
    	if (authentication != null && authentication.getPrincipal() instanceof UsuarioLogado usuarioLogado) {
            if (usuarioLogado.getAdm() != null) {
                model.addAttribute("usuario", usuarioLogado.getAdm());
            } else if (usuarioLogado.getFun() != null) {
                model.addAttribute("usuario", usuarioLogado.getFun());
            } else if (usuarioLogado.getMor() != null) {
                model.addAttribute("usuario", usuarioLogado.getMor());
            } else {
                model.addAttribute("usuario", criarVisitante());
            }
        } else {
            // Sem login: usuário padrão para não quebrar as telas (acesso liberado por enquanto)
            model.addAttribute("usuario", criarVisitante());
        }
    }

    private static Morador criarVisitante() {
        Morador v = new Morador();
        v.setNome("Visitante");
        v.setEmail("");
        v.setSenha("");
        return v;
    }
}