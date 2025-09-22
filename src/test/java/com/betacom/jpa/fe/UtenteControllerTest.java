package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controllers.UtenteController;
import com.betacom.jpa.dto.LoginDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.LoginReq;
import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.response.ResponseObject;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtenteControllerTest {

	@Autowired
    private UtenteController utenteController;

    @Test
    @Order(1)
    public void createUtenteTest() throws AcademyException {
        UtenteReq req = new UtenteReq();
        req.setNome("Paolo");

        ResponseBase r = utenteController.create(req);
        
        Assertions.assertThat(r.isRc()).isTrue();    
        
    }

    @Test
    @Order(2)
    public void createUtenteEsistenteTest() throws AcademyException {
    	UtenteReq req = new UtenteReq();
        req.setNome("Paolo");

        ResponseBase r = utenteController.create(req);
        
        Assertions.assertThat(r.isRc()).isFalse();
        
    }
    
    @Test
    @Order(3)
    public void listUtenteTest() {
        ResponseList<UtenteDTO> r = utenteController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateUtenteTest() throws AcademyException {
    	UtenteReq updateReq = new UtenteReq();
        updateReq.setId(1);
        updateReq.setNome("Paolo Updated");

        ResponseBase r = utenteController.update(updateReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(5)
    public void updateUtenteInesistenteTest() throws AcademyException {
    	UtenteReq updateReq = new UtenteReq();
        updateReq.setId(5);
        updateReq.setNome("Maria");

        ResponseBase r = utenteController.update(updateReq);
        Assertions.assertThat(r.isRc()).isFalse();
    }

    @Test
    @Order(6)
    public void deleteUtenteTest() throws AcademyException {
    	UtenteReq deleteReq = new UtenteReq();
        deleteReq.setId(1);
        deleteReq.setNome("Paolo Updated");

        ResponseBase r = utenteController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(7)
    public void deleteUtenteInesistenteTest() throws AcademyException {
    	UtenteReq deleteReq = new UtenteReq();
        deleteReq.setId(5);
        deleteReq.setNome("Maria");

        ResponseBase r = utenteController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isFalse();
    } 
    
    @Test
    @Order(8)
    public void loginUtenteOkTest() {
        LoginReq req = new LoginReq();
        req.setUser("Paolo");   
        req.setPassword("password");

        ResponseObject<LoginDTO> r = utenteController.login(req);

        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati()).isNotNull();
    }

}
