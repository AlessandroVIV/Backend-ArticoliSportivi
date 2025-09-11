package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>{

	Optional<Marca> findByNome(String nome) throws AcademyException;
	
}
