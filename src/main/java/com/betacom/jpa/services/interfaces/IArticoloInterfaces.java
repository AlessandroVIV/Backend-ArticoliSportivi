package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ArticoloIndumentoReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;

public interface IArticoloInterfaces {

	void createScarpa (ArticoloScarpaReq req) throws AcademyException;
	void createIndumento (ArticoloIndumentoReq req) throws AcademyException;
	
}
