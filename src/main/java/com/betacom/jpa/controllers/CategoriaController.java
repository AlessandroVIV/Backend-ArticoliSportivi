package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.CategoriaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.ICategoriaInterfaces;

@RestController
@RequestMapping("/rest/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaInterfaces categoriaService;
	
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

}
