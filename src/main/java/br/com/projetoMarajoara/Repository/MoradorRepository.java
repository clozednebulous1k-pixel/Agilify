package br.com.projetoMarajoara.Repository;

import br.com.projetoMarajoara.Model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradorRepository extends JpaRepository<Morador,Long> {
	
	Morador findByEmail(String email);
	
}
