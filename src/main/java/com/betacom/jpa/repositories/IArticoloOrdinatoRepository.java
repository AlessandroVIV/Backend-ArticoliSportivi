package com.betacom.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.jpa.models.ArticoloOrdinato;
import com.betacom.jpa.models.Ordini;

public interface IArticoloOrdinatoRepository extends JpaRepository<ArticoloOrdinato, Integer>{

	Optional<ArticoloOrdinato> findByArticoloNome(String nome);

    @Query("SELECT o FROM Ordini o WHERE o.utente.id = :utenteId AND o.dataOrdine IS NULL")
    Optional<Ordini> findOrdineApertoPerUtente(@Param("utenteId") Integer utenteId);
}
