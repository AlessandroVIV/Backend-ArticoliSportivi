package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.requests.UtenteReq;

public interface IUtenteInterfaces {

	Utente createUtente(UtenteReq req) throws AcademyException;
	
	Utente updateUtente(UtenteReq req) throws AcademyException;
	
	Utente deleteUtente(UtenteReq req) throws AcademyException;
	
	List<UtenteDTO> listAll();
	
}
