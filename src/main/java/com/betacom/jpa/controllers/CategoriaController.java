package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.CategoriaDTO;
import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ICategoriaInterfaces;

@RestController
@RequestMapping("/rest/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaInterfaces categoriaService;
	
	@GetMapping("/list")
	public ResponseList<CategoriaDTO> list() {
	    ResponseList<CategoriaDTO> r = new ResponseList<CategoriaDTO>();
	    try {
	        r.setDati(categoriaService.listAll());
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    return r;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody(required = true) CategoriaReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			categoriaService.create(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  CategoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			categoriaService.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  CategoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			categoriaService.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

}
