package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.OrdineRequest;

public interface IOrdineInterfaces {

	void createOrdine(OrdineRequest req) throws AcademyException;
	
	List<OrdineDTO> listAll() throws AcademyException;
	
	OrdineDTO listById(Integer id) throws AcademyException;
	
}
