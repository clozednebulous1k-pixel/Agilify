package br.com.projetoMarajoara.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetoMarajoara.Model.Reserva;
import br.com.projetoMarajoara.Repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepo;

	public List<Reserva> getAllReservas() {
		return reservaRepo.findAll();
	}

	public void save(Reserva reserva) throws IOException {
		if (isPeriodoReservado(reserva.getEspaco(), reserva.getData(), reserva)) {
			throw new IOException("Este espaço já possui uma reserva para este período.");
		}
		reservaRepo.save(reserva);
	}

	public Reserva getById(Long id) {
		Optional<Reserva> optional = reservaRepo.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Reserva não encontrada para o id: " + id));
	}

	public void deleteViaId(long id) {
		reservaRepo.deleteById(id);
	}

	public boolean isPeriodoReservado(String espaco, LocalDate data, Reserva novaReserva) {
		List<Reserva> reservasExistentes = reservaRepo.findByEspacoAndData(espaco, data);

		// Verifica se algum dos períodos selecionados já está ocupado
		for (Reserva reserva : reservasExistentes) {
			boolean manhaOcupada = reserva.getManha() && novaReserva.getManha();
			boolean tardeOcupada = reserva.getTarde() && novaReserva.getTarde();
			boolean noiteOcupada = reserva.getNoite() && novaReserva.getNoite();

			if (manhaOcupada || tardeOcupada || noiteOcupada) {
				return true; // Já existe uma reserva para pelo menos um dos períodos
			}
		}
		return false;
	}
}