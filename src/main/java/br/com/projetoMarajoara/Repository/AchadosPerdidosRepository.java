package br.com.projetoMarajoara.Repository;

import br.com.projetoMarajoara.Model.AchadosPerdidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchadosPerdidosRepository extends JpaRepository<AchadosPerdidos,Long> {
}
