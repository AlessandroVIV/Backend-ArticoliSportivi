package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IMarcaInterfaces;

@RestController
@RequestMapping("/rest/marca")
public class MarcaController {

	@Autowired
	private IMarcaInterfaces marcaInterfaces;
	
	@PostMapping("/createMarca")
	public ResponseBase create(@RequestBody(required = true) MarcaReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			marcaInterfaces.createMarca(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@DeleteMapping("/delete")
	public ResponseBase delete(@RequestBody(required = true) MarcaReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			marcaInterfaces.deleteMarca(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	
}
