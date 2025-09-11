package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;

public interface IMarcaInterfaces {

	void createMarca(MarcaReq req) throws AcademyException;
	
	void updateMarca(MarcaReq req) throws AcademyException;

	void deleteMarca(MarcaReq req) throws AcademyException;
	
}
