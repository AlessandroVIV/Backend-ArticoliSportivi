package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;

public interface IMarcaInterfaces {

	void createMarca(MarcaReq req) throws AcademyException;
	
	void updateMarca(MarcaReq req) throws AcademyException;

	void deleteMarca(MarcaReq req) throws AcademyException;
	
	List<MarcaDTO> listAll();
	
}
