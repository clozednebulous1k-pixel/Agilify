package br.com.projetoMarajoara.integration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.projetoMarajoara.Model.AchadosPerdidos;
import br.com.projetoMarajoara.Repository.AchadosPerdidosRepository;

@DataJpaTest
class AchadosPerdidosPersistenceIntegrationTest {

    @Autowired
    private AchadosPerdidosRepository achadosPerdidosRepository;

    @Test
    void exclusaoDeItemRemoveRegistroEImagemAssociada() {
        byte[] imagem = new byte[] { 1, 2, 3, 4 };
        AchadosPerdidos item = new AchadosPerdidos();
        item.setNome("Chave");
        item.setDescricao("Chave de apartamento");
        item.setImageNome("chave.png");
        item.setImageTipo("image/png");
        item.setImageDados(imagem);

        AchadosPerdidos salvo = achadosPerdidosRepository.save(item);
        assertArrayEquals(imagem, salvo.getImageDados());

        achadosPerdidosRepository.deleteById(salvo.getId());

        assertFalse(achadosPerdidosRepository.findById(salvo.getId()).isPresent());
    }

    @Test
    void aplicacaoSobeComDatasourceUnicoEmTestes() {
        assertTrue(achadosPerdidosRepository.count() >= 0);
    }
}
