package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IMarcaInterfaces;

@CrossOrigin("*")

@RestController
@RequestMapping("/rest/marca")
public class MarcaController {

	@Autowired
	private IMarcaInterfaces marcaInterfaces;
	
	@GetMapping("/listAll")
	public ResponseList<MarcaDTO> listAll() {
	    ResponseList<MarcaDTO> r = new ResponseList<MarcaDTO>();
	    try {
	        r.setDati(marcaInterfaces.listAll());
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    return r;
	}
	
	@PostMapping("/createMarca")
	public ResponseBase create(@RequestBody MarcaReq req) throws AcademyException {
	    return marcaInterfaces.createMarca(req);
	}
	
	@PutMapping("/updateMarca")
	public ResponseBase update(@RequestBody MarcaReq req) throws AcademyException {
	    return marcaInterfaces.updateMarca(req);
	}

	@DeleteMapping("/deleteMarca")
	public ResponseBase delete(@RequestBody MarcaReq req) throws AcademyException {
	    return marcaInterfaces.deleteMarca(req);
	}
	
	
}
