package br.com.projetoMarajoara.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.projetoMarajoara.Model.AchadosPerdidos;
import br.com.projetoMarajoara.Model.Evento;
import br.com.projetoMarajoara.Service.AchadosPerdidosService;

@Controller
public class InfoAchadosController {

    @Autowired
    AchadosPerdidosService as;

    @PostMapping("/addAchado")
    public String addAchado(@ModelAttribute("achado")AchadosPerdidos ac,
                            @RequestParam("image") MultipartFile image) throws IOException {

        as.save(ac, image);
        return "redirect:/adm/achados";
    }
    
    @GetMapping("imgAc/{id}/image")
    public ResponseEntity<byte[]> getEvImage(@PathVariable long id) {
        AchadosPerdidos av = as.getById(id);

        try {
            // Verifica se há imagem associada
            if (!av.getImageNome().equals("")) {
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(av.getImageTipo()))
						.body(av.getImageDados());
            }

            // Se não houver imagem, retorna 204 No Content
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updateAchado")
    public String updateAchado(@ModelAttribute("achado") AchadosPerdidos ap,
                               @RequestParam("image") MultipartFile image) throws IOException {
    	
    	System.out.println(ap.getId());
    	
        AchadosPerdidos oldImage = as.getById(ap.getId());
        if(image.isEmpty()) {
            as.saveButkeepImage(ap, oldImage);
        }else {
            as.save(ap, image);
        }

        return "redirect:/adm/achados";
    }

    @PostMapping("adm/deleteAchado/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        as.deleteViaId(id);
        return "redirect:/adm/achados";
    }

}
