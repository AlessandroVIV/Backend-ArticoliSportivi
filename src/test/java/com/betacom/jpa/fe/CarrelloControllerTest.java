package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.betacom.jpa.controllers.CarrelloController;
import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.CarrelloItemReq;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarrelloControllerTest {

    @Autowired
    private CarrelloController carrelloController;

    @Autowired
    private IUtenteRepository utenteRepository;

    @Autowired
    private IArticoloRepository articoloRepository;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private IGenereRepository genereRepository;

    @Autowired
    private IMarcaRepository marcaRepository;

    private Integer utenteId;
    private Integer articoloId;
    private Integer itemId;

    @BeforeAll
    public void setupAll() {

        Utente u = new Utente();
        u.setNome("Luca");
        u.setCognome("Bianchi");
        u.setUsername("luca.bianchi");
        u.setPassword("test");
        u.setEmail("luca@test.com");

        Carrello c = new Carrello();
        c.setUtente(u);
        u.setCarrello(c);

        utenteRepository.saveAndFlush(u);
        utenteId = u.getId();

        Categoria cat = new Categoria();
        cat.setNome("Console");
        categoriaRepository.saveAndFlush(cat);

        Genere g = new Genere();
        g.setNome("Unisex");
        genereRepository.saveAndFlush(g);

        Marca m = new Marca();
        m.setNome("Microsoft");
        marcaRepository.saveAndFlush(m);

        Articolo a = new Articolo();
        a.setNome("Xbox Series X");
        a.setCategoria(cat);
        a.setGenere(g);
        a.setMarca(m);
        a.setPrezzo(549.99);
        a.setDescrizione("Console next-gen Microsoft");
        a.setUrlImmagine("http://fake.url/xbox.jpg");
        articoloRepository.saveAndFlush(a);

        articoloId = a.getId();
    }

    @Test
    @Order(1)
    public void aggiungiItemTest() {
        CarrelloItemReq req = new CarrelloItemReq();
        req.setArticoloId(articoloId);
        req.setQuantita(2);

        CarrelloItemDTO dto = carrelloController.aggiungiItem(utenteId, req).getBody();

        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getQuantita()).isEqualTo(2);
        Assertions.assertThat(dto.getArticolo().getId()).isEqualTo(articoloId);

        itemId = dto.getId().intValue();
    }

    @Test
    @Order(2)
    public void aggiornaQuantitaTest() {
        CarrelloItemDTO updated = carrelloController
                .aggiornaQuantita(utenteId, itemId, 5)
                .getBody();

        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getQuantita()).isEqualTo(5);
    }

    @Test
    @Order(3)
    public void incrementaQuantitaTest() {
        CarrelloItemDTO updated = carrelloController
                .incrementaQuantita(utenteId, itemId)
                .getBody();

        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getQuantita()).isEqualTo(6);
    }

    @Test
    @Order(4)
    public void decrementaQuantitaTest() {
        CarrelloItemDTO updated = carrelloController
                .decrementaQuantita(utenteId, itemId)
                .getBody();

        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getQuantita()).isEqualTo(5);
    }

    @Test
    @Order(5)
    public void rimuoviItemTest() {
        String response = (String) carrelloController
                .rimuoviItem(utenteId, itemId)
                .getBody();

        Assertions.assertThat(response).contains("Item rimosso");
    }

    @Test
    @Order(6)
    public void rimuoviItemInesistenteTest() {
        Object response = carrelloController.rimuoviItem(utenteId, 99999).getBody();
        Assertions.assertThat(response).isInstanceOf(String.class);
        Assertions.assertThat(response.toString()).contains("non trovato");
    }
}
