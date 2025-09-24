package com.betacom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Carrello;

public interface ICarrelloRepository extends JpaRepository<Carrello, Integer>{

	Carrello findByUtenteId(Integer utenteId);
	
}
