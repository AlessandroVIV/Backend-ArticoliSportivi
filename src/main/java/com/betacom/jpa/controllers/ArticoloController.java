package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.requests.ArticoloIndumentoReq;
import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IArticoloInterfaces;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/articolo")
public class ArticoloController {

	@Autowired
	private IArticoloInterfaces articoloInterfaces;
	
	@Autowired
	private IArticoloRepository articoloRepository;
	
	@GetMapping("/listAll")
	public ResponseList<ArticoloDTO> listAll() {
	    ResponseList<ArticoloDTO> r = new ResponseList<ArticoloDTO>();
	    try {
	        r.setDati(articoloInterfaces.listAll());
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    return r;
	}
	
	@GetMapping("/getArticolo")
	public Articolo getArticolo(@RequestParam Long id) throws AcademyException {
	    return articoloRepository.findById(id).orElse(null);
	}

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
	
	@PutMapping("/updateScarpa")
	public ResponseBase update(@RequestBody (required = true)  ArticoloScarpaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			articoloInterfaces.updateScarpa(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("/updateIndumento")
	public ResponseBase update(@RequestBody (required = true)  ArticoloIndumentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			articoloInterfaces.updateIndumento(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("/deleteArticolo")
	public ResponseBase delete(@RequestParam(required = true) Integer id) {
		ResponseBase responseBase = new ResponseBase();
		try {
			articoloInterfaces.deleteArticolo(id);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}

}
