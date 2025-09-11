package com.betacom.jpa.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.services.interfaces.IMarcaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl implements IMarcaInterfaces{
	
	@Autowired
	private IMarcaRepository marcaRepository;
	
	@Override
	public void createMarca(MarcaReq req) throws AcademyException {
		
	    log.debug("Create Marca: " + req);

	    Marca mar = new Marca();
	    
	    if(marcaRepository.findByMarca(req.getMarca()).isPresent()) throw new AcademyException("Marca già presente nel database!");
	    
	    mar.setMarca(req.getMarca());
	    
	    marcaRepository.save(mar);
		
	}
	
}
