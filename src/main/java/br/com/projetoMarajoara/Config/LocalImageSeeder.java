package br.com.projetoMarajoara.Config;

import br.com.projetoMarajoara.Model.AchadosPerdidos;
import br.com.projetoMarajoara.Model.Evento;
import br.com.projetoMarajoara.Repository.AchadosPerdidosRepository;
import br.com.projetoMarajoara.Repository.EventoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
@Profile("local")
public class LocalImageSeeder {

    @Bean
    CommandLineRunner seedImagensLocais(EventoRepository eventoRepository,
                                        AchadosPerdidosRepository achadosRepository) {
        return args -> {
            ClassPathResource imageResource = new ClassPathResource("static/Images/img_predios.jpg");
            byte[] imageBytes = imageResource.getInputStream().readAllBytes();

            if (eventoRepository.count() == 0) {
                Evento evento = new Evento();
                evento.setDescricao("Assembleia de moradores");
                evento.setData(LocalDate.now().plusDays(3));
                evento.setImageNome("img_predios.jpg");
                evento.setImageTipo("image/jpeg");
                evento.setImageDados(imageBytes);
                eventoRepository.save(evento);
            }
            eventoRepository.findAll().forEach(evento -> {
                if (evento.getImageDados() == null || evento.getImageDados().length == 0) {
                    evento.setImageNome("img_predios.jpg");
                    evento.setImageTipo("image/jpeg");
                    evento.setImageDados(imageBytes);
                    eventoRepository.save(evento);
                }
            });

            if (achadosRepository.count() == 0) {
                AchadosPerdidos achado = new AchadosPerdidos();
                achado.setNome("Chave com chaveiro azul");
                achado.setDescricao("Encontrada próxima ao portão principal.");
                achado.setLugar_encontro("Portaria");
                achado.setHora_encontro(LocalTime.of(10, 30));
                achado.setImageNome("img_predios.jpg");
                achado.setImageTipo("image/jpeg");
                achado.setImageDados(imageBytes);
                achadosRepository.save(achado);
            }
            achadosRepository.findAll().forEach(achado -> {
                if (achado.getImageDados() == null || achado.getImageDados().length == 0) {
                    achado.setImageNome("img_predios.jpg");
                    achado.setImageTipo("image/jpeg");
                    achado.setImageDados(imageBytes);
                    achadosRepository.save(achado);
                }
            });
        };
    }
}
