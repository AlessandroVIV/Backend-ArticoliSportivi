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

import com.betacom.jpa.dto.TagliaDTO;
import com.betacom.jpa.requests.TagliaRequest;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.ITagliaInterfaces;

@CrossOrigin("*")

@RestController
@RequestMapping("/rest/taglia")
public class TagliaController {

	@Autowired
	private ITagliaInterfaces tagliaInterfaces;
	
	@GetMapping("/listAll")
	public ResponseList<TagliaDTO> listAll() {
	    ResponseList<TagliaDTO> r = new ResponseList<TagliaDTO>();
	    try {
	        r.setDati(tagliaInterfaces.listAll());
	        r.setRc(true);
	    } catch (Exception e) {
	        r.setRc(false);
	        r.setMsg(e.getMessage());
	    }
	    return r;
	}
	
	@PostMapping("/createTagliaIndumento")
	public ResponseBase create(@RequestBody(required = true) TagliaRequest req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			tagliaInterfaces.createTaglia(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@PutMapping("/updateTagliaIndumento")
	public ResponseBase update(@RequestBody(required = true) TagliaRequest req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			tagliaInterfaces.updateTaglia(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
	@DeleteMapping("/deleteTagliaIndumento")
	public ResponseBase delete(@RequestBody(required = true) TagliaRequest req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			tagliaInterfaces.deleteTaglia(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
}
