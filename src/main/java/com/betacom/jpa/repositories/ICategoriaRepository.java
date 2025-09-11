package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByNome(String nome) throws AcademyException;
	
}
