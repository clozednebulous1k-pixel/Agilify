package br.com.projetoMarajoara.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projetoMarajoara.Model.Reserva;
import br.com.projetoMarajoara.Service.AchadosPerdidosService;
import br.com.projetoMarajoara.Service.EventoService;
import br.com.projetoMarajoara.Service.ReservaService;

@Controller
@RequestMapping("/morador")
public class PageMoradorController {

    @Autowired
    EventoService es;
    @Autowired
    AchadosPerdidosService as;
    @Autowired
    ReservaService rs;

    @GetMapping("/aluguel")
    public String mostrarPaginaAluguel(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "morador/pagina_aluguel_morador";
    }

    @GetMapping("/perfil")
    public String viewPerfil(){
        return "morador/pagina_perfil_morador";
    }

    /*ver se coloca id ou se vai pelo js ou como q coloca o e-mail do usuario aqui*/
    @GetMapping("/reclamacoes")
    public String viewreclamacoes(){
        return "morador/pagina_reclamacoes_morador";
    }

    @GetMapping("/achados")
    public String viewAchados(Model model){
        model.addAttribute("listAchados", as.getAllItens());
        return "morador/pagina_achados_morador";
    }

    @GetMapping("/eventos")
    public String viewEventos(Model model){
        model.addAttribute("listaEventos", es.getAllEventos());
        return "morador/pagina_eventos_morador";
    }

}
