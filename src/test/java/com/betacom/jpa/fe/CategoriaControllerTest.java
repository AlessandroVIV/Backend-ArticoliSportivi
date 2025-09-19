package com.betacom.jpa.fe;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url(String path) {
        return "http://localhost:" + port + "/rest/categoria" + path;
    }

    @Test
    void testCreateCategoria() {

        CategoriaReq req = new CategoriaReq();
        req.setNome("Sportiva");


        ResponseEntity<ResponseBase> response =
                restTemplate.postForEntity(url("/createCategoria"), req, ResponseBase.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isRc()).isTrue();
    }
}
