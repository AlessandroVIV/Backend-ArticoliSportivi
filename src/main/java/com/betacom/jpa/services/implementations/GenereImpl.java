package com.betacom.jpa.services.implementations;

import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.services.interfaces.IGenereInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GenereImpl implements IGenereInterfaces {

	@Override
	public void createGenere(GenereReq req) throws AcademyException {
		
		
	}

}
