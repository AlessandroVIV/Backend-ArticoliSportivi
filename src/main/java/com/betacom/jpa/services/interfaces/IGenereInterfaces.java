package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.requests.GenereReq;

public interface IGenereInterfaces {

	void createGenere (GenereReq req) throws AcademyException;

	
}
