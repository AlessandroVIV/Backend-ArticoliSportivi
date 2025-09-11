package com.betacom.jpa.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.repositories.ICategoriaRepository;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.services.interfaces.ICategoriaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoriaImpl implements ICategoriaInterfaces{
	
	@Autowired
	private ICategoriaRepository categoriaR;
	
	@Override
	public void create(CategoriaReq req) throws AcademyException {
		// TODO Auto-generated method stub
		Optional<Categoria> c = categoriaR.findByNome(req.getNome());
		
		if(c.isPresent()) {
			throw new AcademyException("categoria gia esistente");
		}
		
		Categoria categoria = new Categoria();
		categoria.setNome(req.getNome());
		
		categoriaR.save(categoria);
		
	}

	
	
}
