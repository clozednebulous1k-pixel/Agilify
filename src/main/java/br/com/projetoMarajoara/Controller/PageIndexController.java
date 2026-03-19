package br.com.projetoMarajoara.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageIndexController {

    @GetMapping("/")
    public String showLoginPage(HttpServletRequest http,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Usuário ou senha inválidos");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Você foi deslogado com sucesso");
        }
        return "login";
    }

    /** Entrada sem login: redireciona para a área do morador. */
    @GetMapping("/entrar")
    public String entrarComoUsuario() {
        return "redirect:/morador/eventos";
    }
    
}
