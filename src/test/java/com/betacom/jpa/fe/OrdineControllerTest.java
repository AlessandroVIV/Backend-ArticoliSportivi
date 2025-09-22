package com.betacom.jpa.fe;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controllers.OrdineController;
import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.models.*;
import com.betacom.jpa.repositories.*;
import com.betacom.jpa.requests.OrdineRequest;
import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.implementations.UtenteImpl;
import com.betacom.jpa.utility.Roles;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdineControllerTest {

    @Autowired
    private OrdineController ordineController;

    private static Integer utenteId;

//    @BeforeAll
//    public static void setupAll(
//            @Autowired UtenteImpl utenteService,
//            @Autowired IUtenteRepository utenteRepository,
//            @Autowired IArticoloRepository articoloRepository,
//            @Autowired ICategoriaRepository categoriaRepository,
//            @Autowired IGenereRepository genereRepository,
//            @Autowired IMarcaRepository marcaRepository) throws Exception {
//    	
//    	
//        // 1. creo un utente tramite service (così viene creato anche il carrello)
//   	
//        UtenteReq req = new UtenteReq();
//        req.setNome("Luca");
//        req.setCognome("Bianchi");
//        req.setUsername("luca.bianchi");
//        req.setPassword("password");
//        req.setEmail("luca@test.com");
//        req.setRole(Roles.USER);
//
//        Utente u = utenteService.createUtente(req);
//        utenteId = u.getId();
//
//        // 2. creo entità necessarie per l'articolo
//        Categoria cat = new Categoria();
//        cat.setNome("Console");
//        categoriaRepository.saveAndFlush(cat);
//
//        Genere g = new Genere();
//        g.setNome("Unisex");
//        genereRepository.saveAndFlush(g);
//
//        Marca m = new Marca();
//        m.setNome("Microsoft");
//        marcaRepository.saveAndFlush(m);
//
//        // 3. creo un articolo completo
//        Articolo a = new Articolo();
//        a.setNome("Xbox Series X");
//        a.setCategoria(cat);
//        a.setGenere(g);
//        a.setMarca(m);
//        a.setPrezzo(549.99);
//        a.setDescrizione("Console next-gen Microsoft");
//        a.setUrlImmagine("fake://img");
//        articoloRepository.saveAndFlush(a);
//
//        // 4. recupero il carrello dell'utente e aggiungo un item
//        Utente uReload = utenteRepository.findById(utenteId).orElseThrow();
//        Carrello c = uReload.getCarrello();
//
//        CarrelloItem item = new CarrelloItem();
//        item.setArticolo(a);
//        item.setQuantita(1);
//        item.setPrezzoTotale(549.99);
//        item.setCarrello(c);
//
//        c.addArticolo(item);
//        utenteRepository.saveAndFlush(uReload);
//
//    }
//
//    @Test
//    @Order(1)
//    public void createOrdineTest(@Autowired IOrdineRepository ordineRepository,
//                                 @Autowired IUtenteRepository utenteRepository) {
//
//        OrdineRequest req = new OrdineRequest();
//        req.setUtenteId(utenteId);
//
//        ResponseBase r = ordineController.create(req);
//        Assertions.assertThat(r.isRc()).isTrue();
//
//        // verifica che sia stato creato un ordine
//        Assertions.assertThat(ordineRepository.findAll()).isNotEmpty();
//
//        // verifica che il carrello dell'utente sia stato svuotato
//        Utente uReload = utenteRepository.findById(utenteId).orElseThrow();
//        Assertions.assertThat(uReload.getCarrello().getArticoli()).isEmpty();
//    }

//    @Test
//    @Order(2)
//    public void listAllTest() {
//        ResponseList<OrdineDTO> r = ordineController.listAll();
//        Assertions.assertThat(r.isRc()).isTrue();
//        Assertions.assertThat(r.getDati()).isNotEmpty();
//    }
//
//    @Test
//    @Order(3)
//    public void getByIdTest() {
//        OrdineDTO first = ordineController.listAll().getDati().get(0);
//        Object body = ordineController.getOrdineById(first.getId()).getBody();
//        Assertions.assertThat(body).isInstanceOf(OrdineDTO.class);
//        Assertions.assertThat(((OrdineDTO) body).getId()).isEqualTo(first.getId());
//    }
//
//    @Test
//    @Order(4)
//    public void getByIdNotFoundTest() {
//        Object body = ordineController.getOrdineById(99999).getBody();
//        Assertions.assertThat(body).isInstanceOf(String.class);
//        Assertions.assertThat(body.toString()).contains("non trovato");
//    }
}
