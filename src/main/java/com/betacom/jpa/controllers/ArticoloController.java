package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.requests.ArticoloScarpaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IArticoloInterfaces;

@RestController
@RequestMapping("/rest/articolo")
public class ArticoloController {

	@Autowired
	private IArticoloInterfaces articoloInterfaces;
	
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
	
}
