package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.services.interfaces.ICategoriaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoriaImpl implements ICategoriaInterfaces{
	
	@Autowired
	private ICategoriaRepository categoriaR;
	
	@Autowired
	private IArticoloRepository articoloR;
	
	@Override
	public void create(CategoriaReq req) throws AcademyException {
		
		Optional<Categoria> c = categoriaR.findByNome(req.getNome());
		
		if(c.isPresent()) {
			throw new AcademyException("categoria gia esistente");
		}
		
		Categoria categoria = new Categoria();
		categoria.setNome(req.getNome());
		
		categoriaR.save(categoria);
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(CategoriaReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Categoria> c = categoriaR.findById(req.getId());
		if (c.isEmpty())
			throw new AcademyException("Categoria non presente in database :" + req.getId());
		if (req.getNome() != null) {
			//controllo se esiste gia una categoria con il nome inserito nell'update
			Optional<Categoria> cat = categoriaR.findByNome(req.getNome());
			if(cat.isPresent()) {
				throw new AcademyException("categoria gia esistente, inserisci un altro nome");
			}
			
			//se non esiste posso effettuare la modifica
			c.get().setNome(req.getNome());
		}
		
		categoriaR.save(c.get());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(CategoriaReq req) throws AcademyException {
		Optional<Categoria> c = categoriaR.findById(req.getId());
		if (c.isEmpty())
			throw new AcademyException("categoria non presente in database");

		List<Articolo> articoliCategoria = articoloR.findByCategoria_Nome(req.getNome());

		if (articoliCategoria.size() > 0)
			throw new AcademyException("Categoria presente per articolo");
		
		categoriaR.delete(c.get());

	}

	@Override
	public List<CategoriaDTO> listAll() {
		// TODO Auto-generated method stub
		log.debug("List");
		List<Categoria> categorie = categoriaR.findAll();
		return categorie.stream()
	             .map(c -> CategoriaDTO.builder()
	                     .id(c.getId())
	                     .nome(c.getNome())
	                     .build())
	             .collect(Collectors.toList());
	}

	
	
}
