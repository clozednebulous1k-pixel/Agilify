package br.com.projetoMarajoara.Config;

import br.com.projetoMarajoara.Model.ADM;
import br.com.projetoMarajoara.Model.Funcionario;
import br.com.projetoMarajoara.Model.Morador;
import br.com.projetoMarajoara.Repository.ADMRepository;
import br.com.projetoMarajoara.Repository.FuncionarioRepository;
import br.com.projetoMarajoara.Repository.MoradorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("local")
public class LocalLoginSeeder {

    @Bean
    CommandLineRunner seedLoginUsuarios(MoradorRepository moradorRepository,
                                        ADMRepository admRepository,
                                        FuncionarioRepository funcionarioRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            String emailMorador = "morador@marajoara.com";
            if (moradorRepository.findByEmail(emailMorador) == null) {
                Morador morador = new Morador();
                morador.setNome("Morador Teste");
                morador.setEmail(emailMorador);
                morador.setSenha(passwordEncoder.encode("123456"));
                moradorRepository.save(morador);
            }

            String emailAdm = "admin@marajoara.com";
            if (admRepository.findByEmail(emailAdm) == null) {
                ADM adm = new ADM();
                adm.setNome("Administrador");
                adm.setEmail(emailAdm);
                adm.setSenha(passwordEncoder.encode("123456"));
                admRepository.save(adm);
            }

            String emailFuncionario = "funcionario@marajoara.com";
            if (funcionarioRepository.findByEmail(emailFuncionario) == null) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome("Funcionario Teste");
                funcionario.setEmail(emailFuncionario);
                funcionario.setSenha(passwordEncoder.encode("123456"));
                funcionarioRepository.save(funcionario);
            }
        };
    }
}
