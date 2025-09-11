package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;

public interface IArticoloRepository extends JpaRepository<Articolo, Integer>{

	Optional<Articolo> findByNome(String nome) throws AcademyException;
	
}
