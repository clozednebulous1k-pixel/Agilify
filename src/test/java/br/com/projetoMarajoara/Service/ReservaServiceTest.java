package br.com.projetoMarajoara.Service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projetoMarajoara.Model.Reserva;
import br.com.projetoMarajoara.Repository.ReservaRepository;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void deveImpedirDuplicidadeDeHorario() {
        Reserva existente = novaReserva("salão", true, false, false);
        Reserva nova = novaReserva("salão", true, false, false);
        when(reservaRepository.findByEspacoAndData("salão", nova.getData())).thenReturn(List.of(existente));

        assertThrows(IOException.class, () -> reservaService.save(nova));
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void devePermitirReservaSemConflitoDePeriodo() {
        Reserva existente = novaReserva("salão", true, false, false);
        Reserva nova = novaReserva("salão", false, true, false);
        when(reservaRepository.findByEspacoAndData("salão", nova.getData())).thenReturn(List.of(existente));

        assertDoesNotThrow(() -> reservaService.save(nova));
        verify(reservaRepository).save(nova);
    }

    @Test
    void disponibilidadeDeveSerCoerenteComReservasExistentes() {
        Reserva existente = novaReserva("quadra", false, true, false);
        Reserva nova = novaReserva("quadra", false, true, false);
        when(reservaRepository.findByEspacoAndData("quadra", nova.getData())).thenReturn(List.of(existente));

        assertTrue(reservaService.isPeriodoReservado("quadra", nova.getData(), nova));
    }

    @Test
    void disponibilidadeDeveRetornarLivreQuandoNaoHaSobreposicao() {
        Reserva existente = novaReserva("quadra", false, true, false);
        Reserva nova = novaReserva("quadra", true, false, false);
        when(reservaRepository.findByEspacoAndData("quadra", nova.getData())).thenReturn(List.of(existente));

        assertFalse(reservaService.isPeriodoReservado("quadra", nova.getData(), nova));
    }

    private Reserva novaReserva(String espaco, boolean manha, boolean tarde, boolean noite) {
        Reserva reserva = new Reserva();
        reserva.setEspaco(espaco);
        reserva.setData(LocalDate.of(2026, 5, 3));
        reserva.setManha(manha);
        reserva.setTarde(tarde);
        reserva.setNoite(noite);
        reserva.setReservado_por("morador@teste.com");
        return reserva;
    }
}
