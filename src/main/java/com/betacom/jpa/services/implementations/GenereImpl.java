package com.betacom.jpa.services.implementations;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.GenereDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.services.interfaces.IGenereInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GenereImpl implements IGenereInterfaces {

	@Autowired
	private IGenereRepository genereR;
	
	@Autowired
	private IArticoloRepository articoloR;
	
	@Override
	public void createGenere(GenereReq req) throws AcademyException {
		
		Optional<Genere> g = genereR.findByNome(req.getNome());
		
		if(g.isPresent()) {
			throw new AcademyException("categoria gia esistente");
		}
		
		Genere genere = new Genere();
		genere.setNome(req.getNome());
		
		genereR.save(genere);
		
	}

	@Override
	public void delete(GenereReq req) throws AcademyException {
		 log.debug("Delete Marca con id: " + req.getId());
		    
		    Optional<Genere> genere = genereR.findByNome(req.getNome());
		    
		    if(genere.isEmpty()) throw new AcademyException("Marca non trovata nel database con id:" + req.getId());

		    List<Articolo> articoliMarca = articoloR.findByGenere_Nome(req.getNome());
		    
		    if(articoliMarca.size() > 0) throw new AcademyException("Marca presente per articolo");

		    genereR.delete(genere.get());
		    
	}

	@Override
	public void update(GenereReq req) throws AcademyException {
	    Optional<Genere> g = genereR.findById(req.getId());
	    if (g.isEmpty()) {
	        throw new AcademyException("Genere inesistente");
	    }
	    if (req.getNome() != null) {
	        Optional<Genere> existing = genereR.findByNome(req.getNome());
	        if (existing.isPresent() && !existing.get().getId().equals(req.getId())) {
	            throw new AcademyException("Genere '" + req.getNome() + "' già esistente");
	        }
	        g.get().setNome(req.getNome());
	    }
	    genereR.save(g.get());
	    
	}

	@Override
	public List<GenereDTO> listAll() {
		log.debug("listAll");
		List<Genere> lG = genereR.findAll();
		return lG.stream()
				.map(g -> GenereDTO.builder()
						.id(g.getId())
						.nome(g.getNome())
						.build())
				.collect(Collectors.toList());
	}
}
