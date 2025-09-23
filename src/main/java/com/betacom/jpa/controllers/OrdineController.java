package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.OrdineRequest;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.response.ResponseList;
import com.betacom.jpa.services.interfaces.IOrdineInterfaces;

@CrossOrigin("*")

@RestController
@RequestMapping("/rest/ordine")
public class OrdineController {

	@Autowired
	private IOrdineInterfaces ordineInterfaces;
	
	@PostMapping("/createOrdine")
	public ResponseBase create(@RequestBody(required = true) OrdineRequest req) {
		ResponseBase responseBase = new ResponseBase();
		try {
			ordineInterfaces.createOrdine(req);
			responseBase.setRc(true);
		} catch (Exception e) {
			responseBase.setRc(false);
			responseBase.setMsg(e.getMessage());
		}
		return responseBase;
	}
	
    @GetMapping("/listAll")
    public ResponseList<OrdineDTO> listAll() {
        ResponseList<OrdineDTO> r = new ResponseList<>();
        try {
            r.setDati(ordineInterfaces.listAll());
            r.setRc(true);
        } catch (Exception e) {
            r.setRc(false);
            r.setMsg(e.getMessage());
        }
        return r;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdineById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ordineInterfaces.listById(id));
        } catch (AcademyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
}
