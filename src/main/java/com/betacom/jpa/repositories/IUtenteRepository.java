package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Utente;

public interface IUtenteRepository extends JpaRepository<Utente, Integer>{

	Optional<Utente> findByUsername(String username);
	Optional<Utente> findByUsernameAndPassword(String userName,String pwd);

}
