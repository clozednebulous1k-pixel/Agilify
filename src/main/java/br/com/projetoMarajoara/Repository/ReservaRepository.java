package br.com.projetoMarajoara.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoMarajoara.Model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {
	List<Reserva> findByEspacoAndData(String espaco, LocalDate data);

}