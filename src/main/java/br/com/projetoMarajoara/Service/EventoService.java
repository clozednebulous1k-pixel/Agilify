package br.com.projetoMarajoara.Service;


import br.com.projetoMarajoara.Model.Evento;
import br.com.projetoMarajoara.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired private EventoRepository eventosRepo;

    public List<Evento> getAllEventos()
    {
        return eventosRepo.findAll();
    }

    public void save(Evento evento, MultipartFile image) throws IOException
    {
        evento.setImageNome(image.getOriginalFilename());
        evento.setImageTipo(image.getContentType());
        evento.setImageDados(image.getBytes());
        eventosRepo.save(evento);
    }

    public void save(Evento evento) throws IOException
    {
        eventosRepo.save(evento);
    }

    public void saveButkeepImage(Evento evento, Evento oldEvento) throws IOException
    {
        evento.setImageNome(oldEvento.getImageNome());
        evento.setImageTipo(oldEvento.getImageTipo());
        evento.setImageDados(oldEvento.getImageDados());
        eventosRepo.save(evento);
    }
    public Evento getById(Long id)
    {
        Optional<Evento> optional = eventosRepo.findById(id);
        Evento evento = null;
        if (optional.isPresent())
            evento = optional.get();
        else
            throw new RuntimeException(
                    "Employee not found for id : " + id);
        return evento;
    }
    public void deleteViaId(long id)
    {
        eventosRepo.deleteById(id);
    }
}