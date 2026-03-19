package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.ADM;
import br.com.projetoMarajoara.Repository.ADMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ADMService {

    @Autowired
    private ADMRepository admRepo;

    public ADM getById(Long id)
    {
        Optional<ADM> optional = admRepo.findById(id);
        ADM adm = null;
        if (optional.isPresent())
            adm = optional.get();
        else
            throw new RuntimeException(
                    "Employee not found for id : " + id);
        return adm;
    }

    public void save(ADM adm) throws IOException
    {
        admRepo.save(adm);
    }

}

