package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controllers.TagliaController;
import com.betacom.jpa.dto.TagliaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.TagliaRequest;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TagliaControllerTest {

	@Autowired
    private TagliaController tagliaController;

    @Test
    @Order(1)
    public void createTagliaTest() throws AcademyException {
        TagliaRequest req = new TagliaRequest();
        req.setNome("xl");

        ResponseBase r = tagliaController.create(req);
        
        Assertions.assertThat(r.isRc()).isTrue();    
        
    }

    @Test
    @Order(2)
    public void createTagliaEsistenteTest() throws AcademyException {
        TagliaRequest req = new TagliaRequest();
        req.setNome("xl");

        ResponseBase r = tagliaController.create(req);
        
        Assertions.assertThat(r.isRc()).isFalse();
        
    }
    
    @Test
    @Order(3)
    public void listTagliaTest() {
        ResponseList<TagliaDTO> r = tagliaController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateTagliaTest() throws AcademyException {
        TagliaRequest updateRequest = new TagliaRequest();
        updateRequest.setId(1);
        updateRequest.setNome("xl Updated");

        ResponseBase r = tagliaController.update(updateRequest);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(5)
    public void updateTagliaInesistenteTest() throws AcademyException {
        TagliaRequest updateRequest = new TagliaRequest();
        updateRequest.setId(5);
        updateRequest.setNome("z");

        ResponseBase r = tagliaController.update(updateRequest);
        Assertions.assertThat(r.isRc()).isFalse();
    }

    @Test
    @Order(6)
    public void deleteTagliaTest() throws AcademyException {
        TagliaRequest deleteRequest = new TagliaRequest();
        deleteRequest.setId(1);
        deleteRequest.setNome("xl Updated");

        ResponseBase r = tagliaController.delete(deleteRequest);
        Assertions.assertThat(r.isRc()).isTrue();
    }
    
    @Test
    @Order(7)
    public void deleteTagliaInesistenteTest() throws AcademyException {
        TagliaRequest deleteRequest = new TagliaRequest();
        deleteRequest.setId(5);
        deleteRequest.setNome("z");

        ResponseBase r = tagliaController.delete(deleteRequest);
        Assertions.assertThat(r.isRc()).isFalse();
    } 
	
}
