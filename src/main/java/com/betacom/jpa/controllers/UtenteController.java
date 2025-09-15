package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IUtenteInterfaces;

@RestController
@RequestMapping("/rest/utente")
public class UtenteController {

	@Autowired
	private IUtenteInterfaces utenteInterfaces;
	
	@PostMapping("/createUtente")
	ResponseBase create(@RequestBody(required = true) UtenteReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			utenteInterfaces.createUtente(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
}
