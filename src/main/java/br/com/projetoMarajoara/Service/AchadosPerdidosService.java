package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.AchadosPerdidos;
import br.com.projetoMarajoara.Repository.AchadosPerdidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AchadosPerdidosService {

    @Autowired
    private AchadosPerdidosRepository achadosRepo;

    public List<AchadosPerdidos> getAllItens()
    {
        return achadosRepo.findAll();
    }


    public void save(AchadosPerdidos achados, MultipartFile image) throws IOException
    {
        achados.setImageNome(image.getOriginalFilename());
        achados.setImageTipo(image.getContentType());
        achados.setImageDados(image.getBytes());
        achadosRepo.save(achados);
    }
    public void saveButkeepImage(AchadosPerdidos achados, AchadosPerdidos oldImage)
    {
        if (oldImage == null){
            achados.setImageNome(oldImage.getImageNome());
            achados.setImageTipo(oldImage.getImageTipo());
            achados.setImageDados(oldImage.getImageDados());
        }
        achadosRepo.save(achados);
    }

    public AchadosPerdidos getById(Long id)
    {
        Optional<AchadosPerdidos> optional = achadosRepo.findById(id);
        AchadosPerdidos achados = null;
        if (optional.isPresent())
            achados = optional.get();
        else
            throw new RuntimeException(
                    "Employee not found for id : " + id);
        return achados;
    }
    public void deleteViaId(long id)
    {
        achadosRepo.deleteById(id);
    }

}
