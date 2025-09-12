package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.models.Genere;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.repositories.IGenereRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.ArticoloIndumentoReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.services.interfaces.IArticoloInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ArticoloImpl implements IArticoloInterfaces{

	@Autowired
	private IArticoloRepository articoloRepository;
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Autowired
	private IGenereRepository genereRepository;
	
	@Autowired
	private IMarcaRepository marcaRepository;
	
	@Override
	public void createScarpa(ArticoloScarpaReq req) throws AcademyException {
		
	    log.debug("Create Scarpa: " + req);

	    Articolo art = new Articolo();
	    
	    if(articoloRepository.findByNome(req.getNomeArticolo()).isPresent()) throw new AcademyException("Articolo già presente nel database!");
	    art.setNome(req.getNomeArticolo());

	    Optional<Categoria> cat = categoriaRepository.findByNome(req.getCategoria());	    
	    if(cat.isEmpty()) throw new AcademyException("Categoria non trovata");    	
	    art.setCategoria(cat.get());
	    
	    art.setTagliaScarpe(req.getTagliaScarpe());
	    
	    art.setDescrizione(req.getDescrizione());
	    
	    Optional<Genere> gen = genereRepository.findByNome(req.getGenere());
	    if(gen.isEmpty()) throw new AcademyException("Genere non trovato");	    
	    art.setGenere(gen.get());

	    Optional<Marca> mar = marcaRepository.findByNome(req.getMarca());
	    if(mar.isEmpty()) throw new AcademyException("Marca non trovata");
	    art.setMarca(mar.get());
	    
	    art.setPrezzo(req.getPrezzo());
	    
	    articoloRepository.save(art);
	    
	}


	@Override
	public void createIndumento(ArticoloIndumentoReq req) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Articolo> findByCategoria(String categoria) throws AcademyException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
