package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.betacom.jpa.controllers.GenereController;
import com.betacom.jpa.dto.GenereDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenereControllerTest {

	@Autowired
    private GenereController genereController;

    @Test
    @Order(1)
    public void createGenereTest() throws AcademyException {
        GenereReq req = new GenereReq();
        req.setNome("Uomo");

        ResponseBase r = genereController.create(req);
        
        Assertions.assertThat(r.isRc()).isTrue();    
        
    }

    @Test
    @Order(2)
    public void createGenereEsistenteTest() throws AcademyException {
        GenereReq req = new GenereReq();
        req.setNome("Uomo");

        ResponseBase r = genereController.create(req);
        
        Assertions.assertThat(r.isRc()).isFalse();
        
    }
    
    @Test
    @Order(3)
    public void listGenereTest() {
        ResponseList<GenereDTO> r = genereController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateGenereTest() throws AcademyException {
        GenereReq updateReq = new GenereReq();
        updateReq.setId(1);
        updateReq.setNome("Uomo Updated");

        ResponseBase r = genereController.update(updateReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(5)
    public void updateGenereInesistenteTest() throws AcademyException {
        GenereReq updateReq = new GenereReq();
        updateReq.setId(5);
        updateReq.setNome("Altro");

        ResponseBase r = genereController.update(updateReq);
        Assertions.assertThat(r.isRc()).isFalse();
    }

    @Test
    @Order(6)
    public void deleteGenereTest() throws AcademyException {
        GenereReq deleteReq = new GenereReq();
        deleteReq.setId(1);
        deleteReq.setNome("Uomo Updated");

        ResponseBase r = genereController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(7)
    public void deleteGenereInesistenteTest() throws AcademyException {
        GenereReq deleteReq = new GenereReq();
        deleteReq.setId(5);
        deleteReq.setNome("Altro");

        ResponseBase r = genereController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isFalse();
    } 
	
}
