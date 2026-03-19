package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.Funcionario;
import br.com.projetoMarajoara.Model.Morador;
import br.com.projetoMarajoara.Repository.FuncionarioRepository;
import br.com.projetoMarajoara.Repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

        @Autowired
        private FuncionarioRepository funcRepo;

        public List<Funcionario> getAllFunc()
        {
            return funcRepo.findAll();
        }

        public void save(Funcionario funcionario) throws IOException
        {
        	funcionario.setUpdatedOn(LocalDateTime.now()); 
            funcRepo.save(funcionario);
        }

        public Funcionario getById(Long id)
        {
            Optional<Funcionario> optional = funcRepo.findById(id);
            Funcionario funcionario = null;
            if (optional.isPresent())
                funcionario = optional.get();
            else
                throw new RuntimeException(
                        "Employee not found for id : " + id);
            return funcionario;
        }

        public void deleteViaId(long id)
        {
            funcRepo.deleteById(id);
        }
        
        public Funcionario getByEmail(String email) {
        	return funcRepo.findByEmail(email);
        }

}
