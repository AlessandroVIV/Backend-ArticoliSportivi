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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.controllers.OrdineController;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.CarrelloItem;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICarrelloItemRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.repositories.IOrdineRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.OrdineRequest;
import com.betacom.jpa.response.ResponseBase;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class OrdineControllerTest {

    @Autowired
    private OrdineController ordineController;
    
    @Autowired
    private IUtenteRepository utenteRepository;
    
    @Autowired
    private ICategoriaRepository categoriaRepository;
    
    @Autowired
    private IGenereRepository genereRepository;
    
    @Autowired
    private IMarcaRepository marcaRepository;
    
    @Autowired
    private IArticoloRepository articoloRepository;
    
    @Autowired
    private ICarrelloItemRepository carrelloItemRepository;

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

        Categoria cat = new Categoria();
        cat.setNome("Running");
        categoriaRepository.saveAndFlush(cat);

        Genere g = new Genere();
        g.setNome("Uomo");
        genereRepository.saveAndFlush(g);

        Marca m = new Marca();
        m.setNome("Nike");
        marcaRepository.saveAndFlush(m);

        Articolo a = new Articolo();
        a.setNome("Air max");
        a.setCategoria(cat);
        a.setGenere(g);
        a.setMarca(m);
        a.setPrezzo(120.0);
        articoloRepository.saveAndFlush(a);

        CarrelloItem item = new CarrelloItem();
        item.setArticolo(a);
        item.setQuantita(1);
        item.setPrezzoTotale(a.getPrezzo());
        item.setCarrello(c);

        c.getArticoli().add(item);
        
        carrelloItemRepository.saveAndFlush(item);
    }

    @Transactional
    @Test
    @Order(1)
    @Rollback(false)
    public void createOrdineTest(@Autowired IOrdineRepository ordineRepository) {

    	Utente u = utenteRepository.findByUsername("luca.bianchi").orElseThrow();

        OrdineRequest req = new OrdineRequest();
        req.setUtenteId(u.getId());

        ResponseBase r = ordineController.create(req);
        Assertions.assertThat(r.isRc()).isTrue();

        // verifica che sia stato creato un ordine
        Assertions.assertThat(ordineRepository.findAll()).isNotEmpty();

        // ricarico il carrello dal DB
        Utente uReload = utenteRepository.findById(u.getId()).orElseThrow();
        Assertions.assertThat(uReload.getCarrello().getArticoli()).isEmpty();
    }
    
    @Transactional
    @Test
    @Order(2)
    public void listAllOrdiniTest() {
        var response = ordineController.listAll();

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.isRc()).isTrue();
        Assertions.assertThat(response.getDati())
                  .isNotNull()
                  .isNotEmpty(); // ci aspettiamo almeno 1 ordine creato nel test precedente
    }

    @Transactional
    @Test
    @Order(3)
    public void getOrdineByIdTest(@Autowired IOrdineRepository ordineRepository) {
        // recupero un ordine appena creato
        var ordine = ordineRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nessun ordine trovato"));

        var response = ordineController.getOrdineById(ordine.getId());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        Assertions.assertThat(response.getBody()).isNotNull();
    }


}
