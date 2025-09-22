package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.controllers.ArticoloController;
import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.ArticoloIndumentoReq;
import com.betacom.jpa.requests.ArticoloReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticoloControllerTest {

	@Autowired
    private ArticoloController articoloController;

    @BeforeAll
    public static void setupAll(
            @Autowired ICategoriaRepository categoriaRepository,
            @Autowired IGenereRepository genereRepository,
            @Autowired IMarcaRepository marcaRepository) {
        
        Categoria cat1 = new Categoria();
        cat1.setNome("Scarpe");
        categoriaRepository.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNome("Indumenti");
        categoriaRepository.save(cat2);

        Genere g1 = new Genere();
        g1.setNome("Uomo");
        genereRepository.save(g1);

        Genere g2 = new Genere();
        g2.setNome("Donna");
        genereRepository.save(g2);

        Marca m1 = new Marca();
        m1.setNome("Nike");
        marcaRepository.save(m1);

        Marca m2 = new Marca();
        m2.setNome("Adidas");
        marcaRepository.save(m2);

        categoriaRepository.flush();
        genereRepository.flush();
        marcaRepository.flush();
    }

    @Test
    @Order(1)
    public void createScarpaTest() {
        ArticoloScarpaReq req = new ArticoloScarpaReq();
        req.setNome("Nike Air Test");
        req.setMarca("Nike");
        req.setCategoria("Scarpe");
        req.setGenere("Uomo");

        ResponseBase r = articoloController.create(req);
        Assertions.assertThat(r.isRc()).isTrue();
    }

    @Test
    @Order(2)
    public void createIndumentoTest() {
        ArticoloIndumentoReq req = new ArticoloIndumentoReq();
        req.setNomeArticolo("Maglietta Running");
        req.setMarca("Adidas");
        req.setCategoria("Indumenti");
        req.setGenere("Donna");

        ResponseBase r = articoloController.createIndumento(req);
        Assertions.assertThat(r.isRc()).isTrue();
    }

    @Test
    @Order(3)
    public void listAllTest() {
        ResponseList<ArticoloDTO> r = articoloController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati()).isNotEmpty();
    }

    @Test
    @Order(4)
    public void getByCategoriaTest() {
        ResponseList<ArticoloDTO> r = articoloController.getByCategoria("Scarpe");
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati()).isNotEmpty();
    }

    @Test
    @Order(5)
    public void getByGenereTest() {
        ResponseList<ArticoloDTO> r = articoloController.getByGenere("Uomo");
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati()).isNotEmpty();
    }

    @Test
    @Order(6)
    public void updateArticoloTest() {
        // recuperiamo il primo articolo creato per prendere l'ID reale
        ResponseList<ArticoloDTO> list = articoloController.listAll();
        Assertions.assertThat(list.getDati()).isNotEmpty();

        ArticoloDTO articolo = list.getDati().get(0);

        ArticoloReq updateReq = new ArticoloReq();
        updateReq.setId(articolo.getId());
        updateReq.setNome("Nike Air Updated");

        ResponseBase r = articoloController.update(updateReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }

    @Test
    @Order(7)
    public void deleteArticoloTest() {
        // prendiamo l'ID reale dell'articolo da cancellare
        ResponseList<ArticoloDTO> list = articoloController.listAll();
        Assertions.assertThat(list.getDati()).isNotEmpty();

        Integer id = list.getDati().get(0).getId();
        ResponseBase r = articoloController.delete(id);
        Assertions.assertThat(r.isRc()).isTrue();
    }

    @Test
    @Order(8)
    public void deleteArticoloInesistenteTest() {
        ResponseBase r = articoloController.delete(9999);
        Assertions.assertThat(r.isRc()).isFalse();
    }
	
}
