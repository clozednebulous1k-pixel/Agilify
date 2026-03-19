package br.com.projetoMarajoara.Controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.projetoMarajoara.Model.Funcionario;
import br.com.projetoMarajoara.Service.FuncionarioService;

@Controller
public class InfoFuncController {

    @Autowired
    FuncionarioService fs;    
    
    @Autowired
    PasswordEncoder ps;
    
    @PostMapping("/addFunc")
    public String addFunc(@ModelAttribute("func") Funcionario func) throws IOException {
    	String senhaCriptografada = ps.encode(func.getSenha());
        func.setSenha(senhaCriptografada);
        fs.save(func);
        return "redirect:/adm/configuracoes";
    }

    @PostMapping("/updatePerfilFunc")
    public String updatePerfilFunc(@ModelAttribute("func") Funcionario func) throws IOException {
    	Funcionario funcionario = fs.getByEmail(func.getEmail());
    	if(!func.getSenha().contains("$2a$10$")) {
        	String senhaCriptografada = ps.encode(func.getSenha());
    		func.setUpdatedOn(LocalDateTime.now());
            func.setSenha(senhaCriptografada);
    	}
    	func.setId(funcionario.getId());
        fs.save(func);
        return "redirect:/adm/configuracoes";
    }
    
    @PostMapping("/updateFunc")
    public String updateFunc(@ModelAttribute("func") Funcionario func) throws IOException {
    	if(!func.getSenha().contains("$2a$10$")) {
        	String senhaCriptografada = ps.encode(func.getSenha());
    		func.setUpdatedOn(LocalDateTime.now());
            func.setSenha(senhaCriptografada);
    	}
        fs.save(func);
        return "redirect:/adm/configuracoes";
    }
    
    @GetMapping("/deleteFunc/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        fs.deleteViaId(id);
        return "redirect:/adm/configuracoes";
    }

}
