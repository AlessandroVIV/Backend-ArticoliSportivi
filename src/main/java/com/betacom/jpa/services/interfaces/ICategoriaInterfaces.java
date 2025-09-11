package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CategoriaReq;

public interface ICategoriaInterfaces {

	void create(CategoriaReq req) throws AcademyException;

}
