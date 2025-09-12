package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Genere;

public interface IGenereRepository extends JpaRepository<Genere, Integer>{

	Optional<Genere> findByNome(String nome) throws AcademyException;
	
}
