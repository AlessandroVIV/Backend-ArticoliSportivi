package com.betacom.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Ordini;

public interface IOrdineRepository extends JpaRepository<Ordini, Integer>{

	List<Ordini> findByUtenteId(Integer utenteId);
	
}
