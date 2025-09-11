package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.services.interfaces.IGenereInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GenereImpl implements IGenereInterfaces {

	@Autowired
	private IGenereRepository genereR;
	
	@Override
	public void createGenere(GenereReq req) throws AcademyException {
		
		Optional<Genere> g = genereR.findByGenere(req.getGenere());
		
		if(g.isPresent()) {
			throw new AcademyException("categoria gia esistente");
		}
		
		Genere genere = new Genere();
		genere.setGenere(req.getGenere());
		
		genereR.save(genere);
		
	}

}
