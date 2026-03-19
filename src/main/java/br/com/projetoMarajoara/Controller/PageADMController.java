package br.com.projetoMarajoara.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projetoMarajoara.Model.Evento;
import br.com.projetoMarajoara.Service.AchadosPerdidosService;
import br.com.projetoMarajoara.Service.EventoService;
import br.com.projetoMarajoara.Service.FuncionarioService;
import br.com.projetoMarajoara.Service.MoradorService;
import br.com.projetoMarajoara.Service.ReservaService;


@Controller
@RequestMapping("/adm")
public class PageADMController {

    @Autowired
    EventoService es;
    @Autowired
    AchadosPerdidosService as;
    @Autowired
    FuncionarioService fs;
    @Autowired
    MoradorService ms;
    @Autowired
    ReservaService rs;

    @GetMapping("/aluguel")
    public String viewAluguel(Model model){
    	model.addAttribute("listaAluguel", rs.getAllReservas());
        return "adm/pagina_aluguel_ADM";
    }

    @GetMapping("/perfil")
    public String viewPerfil(){
        return "adm/pagina_perfil_ADM";
    }

    @GetMapping("/reclamacoes")
    public String viewreclamacoes(){
        return "adm/pagina_reclamacoes_ADM";
    }

    @GetMapping("/achados")
    public String viewAchados(Model model){
        model.addAttribute("listaAchados", as.getAllItens());
        return "adm/pagina_achados_ADM";
    }

    @GetMapping("/configuracoes")
    public String viewFuncionarios(Model model){
        model.addAttribute("listaFuncionarios", fs.getAllFunc());
        model.addAttribute("listaMoradores", ms.getAllMorador());
        return "adm/pagina_configuracoes_ADM";
    }
    
    @GetMapping("/viewUpdateFuncForm/{id}")
    public String viewUpdateFuncionario(Model model, @PathVariable(value = "id") long id){
        model.addAttribute("funcionario", fs.getById(id));
        return "adm/pagina_update_func_ADM";
    }

    @GetMapping("/eventos")
    public String viewEventos(Model model){
        model.addAttribute("listaEventos", es.getAllEventos());
        model.addAttribute("evento", new Evento());
        return "adm/pagina_eventos_ADM";
    }
    
    
}
