package br.com.projetoMarajoara.Service;

import br.com.projetoMarajoara.Model.Morador;
import br.com.projetoMarajoara.Repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

	@Autowired
	private MoradorRepository morRepo;

	public List<Morador> getAllMorador() {
		return morRepo.findAll();
	}

	public void save(Morador morador) throws IOException {
		morRepo.save(morador);
	}

	public Morador getById(Long id) {
		Optional<Morador> optional = morRepo.findById(id);
		Morador morador = null;
		if (optional.isPresent())
			morador = optional.get();
		else
			throw new RuntimeException("Employee not found for id : " + id);
		return morador;
	}

	public void deleteViaId(long id) {
		morRepo.deleteById(id);
	}

	public Morador getBtEmail(String email) {
		return morRepo.findByEmail(email);
	}
}
