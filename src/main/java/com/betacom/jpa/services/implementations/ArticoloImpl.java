package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.ArticoloDTO;
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
import com.betacom.jpa.requests.ArticoloReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.services.interfaces.IArticoloInterfaces;
import com.betacom.jpa.utility.Builders;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ArticoloImpl extends Builders implements IArticoloInterfaces{

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
	    
	    if(articoloRepository.findByNome(req.getNome()).isPresent()) throw new AcademyException("Articolo già presente nel database!");
	    art.setNome(req.getNome());

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
	    
	    art.setUrlImmagine(req.getUrlImmagine());
	    
	    articoloRepository.save(art);
	    
	}

	@Override
	public List<ArticoloDTO> findByCategoria(String categoria) throws AcademyException {
		
		log.debug("ListByAttività:" + categoria);

		List<Articolo> lC = articoloRepository.findByCategoria_Nome(categoria);
		
		return buildListArticoloDTO(lC);
		
	}

	@Override
	public void createIndumento(ArticoloIndumentoReq req) throws AcademyException {
		
		log.debug("Create Scarpa: " + req);

	    Articolo art = new Articolo();
	    
	    if(articoloRepository.findByNome(req.getNomeArticolo()).isPresent()) throw new AcademyException("Articolo già presente nel database!");
	    art.setNome(req.getNomeArticolo());

	    Optional<Categoria> cat = categoriaRepository.findByNome(req.getCategoria());	    
	    if(cat.isEmpty()) throw new AcademyException("Categoria non trovata");    	
	    art.setCategoria(cat.get());
	    
	    art.setTagliaIndumento(req.getTagliaIndumento());
	    
	    art.setDescrizione(req.getDescrizione());
	    
	    Optional<Genere> gen = genereRepository.findByNome(req.getGenere());
	    if(gen.isEmpty()) throw new AcademyException("Genere non trovato");	    
	    art.setGenere(gen.get());

	    Optional<Marca> mar = marcaRepository.findByNome(req.getMarca());
	    if(mar.isEmpty()) throw new AcademyException("Marca non trovata");
	    art.setMarca(mar.get());
	    
	    art.setPrezzo(req.getPrezzo());
	    
	    art.setUrlImmagine(req.getUrlImmagine());
	    
	    articoloRepository.save(art);
	}

	@Override
	public List<ArticoloDTO> findByGenere(String genere) throws AcademyException {
		
		log.debug("ListByGenere:" + genere);

		List<Articolo> lC = articoloRepository.findByGenere_Nome(genere);
		
		return buildListArticoloDTO(lC);
	}

	@Override
	public List<ArticoloDTO> findByMarca(String marca) throws AcademyException {
		
		log.debug("ListByMarca:" + marca);

		List<Articolo> lC = articoloRepository.findByMarca_Nome(marca);
		
		return buildListArticoloDTO(lC);
	}

	@Override
	public List<ArticoloDTO> listAll() {
	    List<Articolo> articoli = articoloRepository.findAll();

	    return articoli.stream()
	            .map(a -> ArticoloDTO.builder()
	                    .id(a.getId())
	                    .nomeArticolo(a.getNome())                   
	                    .descrizione(a.getDescrizione())
	                    .prezzo(a.getPrezzo())
	                    .tagliaScarpe(a.getTagliaScarpe())
	                    .tagliaIndumento(a.getTagliaIndumento())
	                    .marca(a.getMarca().getNome())               
	                    .categoria(a.getCategoria().getNome())       
	                    .genere(a.getGenere().getNome())  
	                    .urlImmagine(a.getUrlImmagine())
	                    .build()
	            )
	            .collect(Collectors.toList());
	}


	

	@Override
	public void updateArticolo(ArticoloReq req) throws AcademyException {
	    log.debug("Update Articolo: " + req);

	    Articolo art = articoloRepository.findById(req.getId())
	            .orElseThrow(() -> new AcademyException("Articolo non trovato con id: " + req.getId()));

	    if (req.getCategoria() != null) {
	        Categoria categoria = categoriaRepository.findByNome(req.getCategoria())
	                .orElseThrow(() -> new AcademyException("Categoria inesistente!"));
	        art.setCategoria(categoria);
	    }

	    if (req.getGenere() != null) {
	        Genere genere = genereRepository.findByNome(req.getGenere())
	                .orElseThrow(() -> new AcademyException("Genere inesistente!"));
	        art.setGenere(genere);
	    }

	    if (req.getMarca() != null) {
	        Marca marca = marcaRepository.findByNome(req.getMarca())
	                .orElseThrow(() -> new AcademyException("Marca inesistente!"));
	        art.setMarca(marca);
	    }

	    if (req.getDescrizione() != null) art.setDescrizione(req.getDescrizione());
	    if (req.getNome() != null) art.setNome(req.getNome());
	    if (req.getPrezzo() != null) art.setPrezzo(req.getPrezzo());
	    if (req.getUrlImmagine() != null) art.setUrlImmagine(req.getUrlImmagine());

	    // logica per distinguere
	    if (req.getTagliaScarpe() != null && req.getTagliaIndumento() != null)  {
	        throw new AcademyException ("Entrambe le taglie non vanno bene");
	        
	    } if (req.getTagliaScarpe() != null && art.getTagliaScarpe() != null)  {
	        art.setTagliaScarpe(req.getTagliaScarpe());
	    }  if (req.getTagliaIndumento() != null && art.getTagliaIndumento() != null)  {
	        art.setTagliaIndumento(req.getTagliaIndumento());
	    }

	    articoloRepository.save(art);
	}


	@Override
	public void deleteArticolo(Integer id) throws AcademyException {
		
		log.debug("Delete Scarpa con id: " + id);
	    
	    Optional<Articolo> art = articoloRepository.findById(id);
	    
	    if(art.isEmpty()) throw new AcademyException("Articolo non trovato nel database con id:" + id);

	    articoloRepository.delete(art.get());
		
	}

}
