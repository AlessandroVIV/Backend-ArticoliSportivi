package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.requests.UtenteReq;

public interface IUtenteInterfaces {

	Utente createUtente(UtenteReq req) throws AcademyException;
	
}
