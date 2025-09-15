package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
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
	
	@GetMapping("/listAll")
	public ResponseList<UtenteDTO> listAll() {
		ResponseList<UtenteDTO> r = new ResponseList<UtenteDTO>();
		try {
			r.setDati(utenteInterfaces.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("/updateUtente")
	public ResponseBase update(@RequestBody (required = true)  UtenteReq req) {
		ResponseBase r = new ResponseBase();
		try {
			utenteInterfaces.updateUtente(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("/deleteUtente")
	public ResponseBase delete(@RequestBody(required = true) UtenteReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			utenteInterfaces.deleteUtente(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
}
