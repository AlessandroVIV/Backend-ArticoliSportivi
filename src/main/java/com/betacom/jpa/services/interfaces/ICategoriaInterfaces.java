package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CategoriaReq;

public interface ICategoriaInterfaces {

	void create(CategoriaReq req) throws AcademyException;

	void update(CategoriaReq req) throws AcademyException;
	
	List<CategoriaDTO> listAll();

	void delete(CategoriaReq req) throws AcademyException;

}
