package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.dto.GenereDTO;
import com.betacom.jpa.requests.ArticoloIndumentoReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IArticoloInterfaces;
import com.betacom.jpa.services.interfaces.ICategoriaInterfaces;

@RestController
@RequestMapping("/rest/articolo")
public class ArticoloController {

	@Autowired
	private IArticoloInterfaces articoloInterfaces;
	
	@Autowired
	private ICategoriaInterfaces categoriaInterfaces;
	
	@PostMapping("/createScarpa")
	public ResponseBase create(@RequestBody(required = true) ArticoloScarpaReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			articoloInterfaces.createScarpa(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@PostMapping("/createIndumento")
	public ResponseBase createIndumento(@RequestBody(required = true) ArticoloIndumentoReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			articoloInterfaces.createIndumento(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseList<ArticoloDTO> getByCategoria(@RequestParam String nome) {
		
	    ResponseList<ArticoloDTO> r = new ResponseList<ArticoloDTO>();
	    
	    try {
	        r.setDati(articoloInterfaces.findByCategoria(nome));
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    
	    return r;
	}

	@GetMapping("/genere/{genere}")
	public ResponseList<ArticoloDTO> getByGenere(@RequestParam String nome) {
		
	    ResponseList<ArticoloDTO> r = new ResponseList<ArticoloDTO>();
	    
	    try {
	        r.setDati(articoloInterfaces.findByGenere(nome));
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    
	    return r;
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseList<ArticoloDTO> getByMarca(@RequestParam String nome) {
		
	    ResponseList<ArticoloDTO> r = new ResponseList<ArticoloDTO>();
	    
	    try {
	        r.setDati(articoloInterfaces.findByMarca(nome));
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    
	    return r;
	}
	
}
