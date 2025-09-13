package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.TagliaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.TagliaIndumento;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ITagliaRepository;
import com.betacom.jpa.requests.TagliaRequest;
import com.betacom.jpa.services.interfaces.ITagliaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TagliaImpl implements ITagliaInterfaces{

	@Autowired
	private ITagliaRepository tagliaRepository;
	
	@Autowired
	private IArticoloRepository articoloRepository;
	
	@Override
	public List<TagliaDTO> listAll() {
		
	    List<TagliaIndumento> lc = tagliaRepository.findAll();

	    return lc.stream()
	             .map(c -> TagliaDTO
	            		 .builder()
	                     .id(c.getId())
	                     .nome(c.getNome())
	                     .build())
	             .collect(Collectors.toList());
	}


	@Override
	public void createTaglia(TagliaRequest req) throws AcademyException {
		
	    log.debug("Create Taglia: " + req);

	    TagliaIndumento tagliaIndumento = new TagliaIndumento();
	    
	    if(tagliaRepository.findByNome(req.getNome()).isPresent()) throw new AcademyException("Taglia già presente nel database!");
	    
	    tagliaIndumento.setNome(req.getNome());
	    
	    tagliaRepository.save(tagliaIndumento);
		
	}

	@Override
	public void updateTaglia(TagliaRequest req) throws AcademyException {
	    log.debug("Update Taglia: " + req);

	    TagliaIndumento tagliaInd = tagliaRepository.findById(req.getId())
	            .orElseThrow(() -> new AcademyException("Taglia non trovata nel database con id: " + req.getId()));

	    if (req.getNome() != null && !req.getNome().equals(tagliaInd.getNome())) {
	        if (tagliaRepository.findByNome(req.getNome()).isPresent()) throw new AcademyException("Taglia già presente nel database!");        
	        tagliaInd.setNome(req.getNome());
	    }

	    tagliaRepository.save(tagliaInd);
		
	}

	@Override
	public void deleteTaglia(TagliaRequest req) throws AcademyException {
	    log.debug("Delete Taglia con id: " + req.getId());
	    
	    Optional<TagliaIndumento> tagliaInd = tagliaRepository.findByNome(req.getNome());
	    
	    if(tagliaInd.isEmpty()) throw new AcademyException("Taglia non trovata nel database con id:" + req.getId());

	    List<Articolo> articoliMarca = articoloRepository.findByTagliaIndumento(req.getNome());
	    
	    if(articoliMarca.size() > 0) throw new AcademyException("Taglia presente per articolo");

	    tagliaRepository.delete(tagliaInd.get());
		
	}
	
}
