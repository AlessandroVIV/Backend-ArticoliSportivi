package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.betacom.jpa.controllers.CategoriaController;
import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaControllerTest {

	@Autowired
    private CategoriaController categoriaController;

    @Test
    @Order(1)
    public void createCategoriaTest() throws AcademyException {
        CategoriaReq req = new CategoriaReq();
        req.setNome("Sportiva");

        ResponseBase r = categoriaController.create(req);
        
        Assertions.assertThat(r.isRc()).isTrue();    
        
    }

    @Test
    @Order(2)
    public void createCategoriaEsistenteTest() throws AcademyException {
        CategoriaReq req = new CategoriaReq();
        req.setNome("Sportiva");

        ResponseBase r = categoriaController.create(req);
        
        Assertions.assertThat(r.isRc()).isFalse();
        
    }
    
    @Test
    @Order(3)
    public void listCategoriaTest() {
        ResponseList<CategoriaDTO> r = categoriaController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateCategoriaTest() throws AcademyException {
        CategoriaReq updateReq = new CategoriaReq();
        updateReq.setId(1);
        updateReq.setNome("Sportiva Updated");

        ResponseBase r = categoriaController.update(updateReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(5)
    public void updateCategoriaInesistenteTest() throws AcademyException {
        CategoriaReq updateReq = new CategoriaReq();
        updateReq.setId(5);
        updateReq.setNome("Running");

        ResponseBase r = categoriaController.update(updateReq);
        Assertions.assertThat(r.isRc()).isFalse();
    }

    @Test
    @Order(6)
    public void deleteCategoriaTest() throws AcademyException {
        CategoriaReq deleteReq = new CategoriaReq();
        deleteReq.setId(1);
        deleteReq.setNome("Sportiva Updated");

        ResponseBase r = categoriaController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(7)
    public void deleteCategoriaInesistenteTest() throws AcademyException {
        CategoriaReq deleteReq = new CategoriaReq();
        deleteReq.setId(5);
        deleteReq.setNome("Running");

        ResponseBase r = categoriaController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isFalse();
    } 
	
}
