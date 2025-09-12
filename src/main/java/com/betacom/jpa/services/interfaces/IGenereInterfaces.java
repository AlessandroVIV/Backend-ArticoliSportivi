package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.GenereDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.GenereReq;

public interface IGenereInterfaces {

	void createGenere (GenereReq req) throws AcademyException;
	void delete(GenereReq req) throws AcademyException;
	void update(GenereReq req) throws AcademyException;
	
	List<GenereDTO> listAll();

	
}
