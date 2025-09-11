package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IGenereInterfaces;

@RestController
@RequestMapping("/rest/genere")
public class GenereController {

	@Autowired
	private IGenereInterfaces genereInterfaces;
	
	@PostMapping("/createGenere")
	ResponseBase create(@RequestBody(required = true) GenereReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			genereInterfaces.createGenere(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
}
