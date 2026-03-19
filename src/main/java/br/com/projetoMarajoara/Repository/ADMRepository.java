package br.com.projetoMarajoara.Repository;

import br.com.projetoMarajoara.Model.ADM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADMRepository extends JpaRepository<ADM,Long> {

	ADM findByEmail(String email);
	
}
