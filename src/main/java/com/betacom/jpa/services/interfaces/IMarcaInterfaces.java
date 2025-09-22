package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;

public interface IMarcaInterfaces {

	ResponseBase createMarca(MarcaReq req) throws AcademyException;
	
	ResponseBase updateMarca(MarcaReq req) throws AcademyException;

	ResponseBase deleteMarca(MarcaReq req) throws AcademyException;
	
	List<MarcaDTO> listAll();
	
}
