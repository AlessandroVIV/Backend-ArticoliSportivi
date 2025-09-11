package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.services.interfaces.IMarcaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl implements IMarcaInterfaces{
	
	@Autowired
	private IMarcaRepository marcaRepository;
	
	@Autowired
	private IArticoloRepository articoloRepository;
	
	@Override
	public void createMarca(MarcaReq req) throws AcademyException {
		
	    log.debug("Create Marca: " + req);

	    Marca mar = new Marca();
	    
	    if(marcaRepository.findByNome(req.getNome()).isPresent()) throw new AcademyException("Marca già presente nel database!");
	    
	    mar.setNome(req.getNome());
	    
	    marcaRepository.save(mar);
		
	}
	
	@Override
	public void updateMarca(MarcaReq req) throws AcademyException {
		
		log.debug("Update Marca: " + req);
		
		Optional<Marca> m = marcaRepository.findById(req.getId());
		
		if(m.isEmpty()) throw new AcademyException("Marca non trovata nel database con id: " + req.getId());

		Marca mar = m.get();
		
		if(req.getNome() != null) {
			mar.setNome(req.getNome());
		}
		
		marcaRepository.save(mar); 
		
	}
	
	@Override
	public void deleteMarca(MarcaReq req) throws AcademyException {
		
	    log.debug("Delete Marca con id: " + req.getId());
	    
	    Optional<Marca> mar = marcaRepository.findByNome(req.getNome());
	    
	    if(mar.isEmpty()) throw new AcademyException("Marca non trovata nel database con id:" + req.getId());

	    List<Articolo> articoliMarca = articoloRepository.findByMarca_Nome(req.getNome());
	    
	    if(articoliMarca.size() > 0) throw new AcademyException("Marca presente per articolo");

	    marcaRepository.delete(mar.get());
	    
	}
	
}
