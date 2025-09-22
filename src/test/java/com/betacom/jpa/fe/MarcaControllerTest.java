package com.betacom.jpa.fe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.jpa.controllers.MarcaController;
import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MarcaControllerTest {

    @Autowired
    private MarcaController marcaController;

    @Test
    @Order(1)
    public void createMarcaTest() throws AcademyException {
        MarcaReq req = new MarcaReq();
        req.setNome("Nike");

        ResponseBase r = marcaController.create(req);

        req = new MarcaReq();
        req.setNome("Puma");
        r = marcaController.create(req);
        Assertions.assertThat(r.isRc()).isTrue();

        req = new MarcaReq();
        req.setNome("Reebok");
        r = marcaController.create(req);      
        
    }

    @Test
    @Order(2)
    public void listMarcaTest() {
        ResponseList<MarcaDTO> r = marcaController.listAll();
        Assertions.assertThat(r.isRc()).isTrue();
        Assertions.assertThat(r.getDati().size()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void updateMarcaTest() throws AcademyException {
        MarcaReq updateReq = new MarcaReq();
        updateReq.setId(1);
        updateReq.setNome("Nike Updated");

        ResponseBase r = marcaController.update(updateReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }

    @Test
    @Order(4)
    public void deleteMarcaTest() throws AcademyException {
        MarcaReq deleteReq = new MarcaReq();
        deleteReq.setId(2);
        deleteReq.setNome("Puma");

        ResponseBase r = marcaController.delete(deleteReq);
        Assertions.assertThat(r.isRc()).isTrue();
    }
}
