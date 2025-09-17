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

import com.betacom.jpa.dto.GenereDTO;
import com.betacom.jpa.requests.GenereReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IGenereInterfaces;

@CrossOrigin("*")

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
	

	@PutMapping("/updateGenere")
	public ResponseBase update(@RequestBody (required = true)  GenereReq req) {
		ResponseBase r = new ResponseBase();
		try {
			genereInterfaces.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("/deleteGenere")
	public ResponseBase delete(@RequestBody(required = true) GenereReq req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			genereInterfaces.delete(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}

	

	@GetMapping("/listAll")
	public ResponseList<GenereDTO> listAll() {
		ResponseList<GenereDTO> r = new ResponseList<GenereDTO>();
		try {
			r.setDati(genereInterfaces.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	}