package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.TagliaIndumento;

public interface ITagliaRepository extends JpaRepository<TagliaIndumento, Integer>{

	Optional<TagliaIndumento> findByNome(String nome) throws AcademyException;
	
}
