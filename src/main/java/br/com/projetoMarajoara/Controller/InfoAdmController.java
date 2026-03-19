package br.com.projetoMarajoara.Controller;

import br.com.projetoMarajoara.Model.ADM;
import br.com.projetoMarajoara.Service.ADMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class InfoAdmController {

    @Autowired
    ADMService as;
    
    @Autowired
    PasswordEncoder ps;

    @PostMapping("/updateADM")
    public String saveADM(@RequestBody ADM adm) throws IOException {
    	String senhaCriptografada = ps.encode(adm.getSenha());
        adm.setSenha(senhaCriptografada);
        as.save(adm);
        return "redirect:/adm/eventos";
    }

}
