package com.betacom.jpa.fe;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;



@Suite
@SelectClasses({
	CategoriaControllerTest.class,
	MarcaControllerTest.class,
	UtenteControllerTest.class,
	ArticoloControllerTest.class,
	CarrelloControllerTest.class
			})

@SpringBootTest
class BackendArticoliSportiviApplicationTests {

	@Test
	void contextLoads() {
	}

}
