package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.requests.CarrelloItemReq;
import com.betacom.jpa.services.interfaces.ICarrelloItemInterfaces;

@RestController
@RequestMapping("/rest/carrello")
public class CarrelloController {

    @Autowired
    private ICarrelloItemInterfaces carrelloItemInterfaces;
    
    @PostMapping("/{utenteId}/items")
    public ResponseEntity<CarrelloItemDTO> aggiungiItem(
            @PathVariable Integer utenteId,
            @RequestBody CarrelloItemReq req) {
        return ResponseEntity.ok(carrelloItemInterfaces.aggiungiItem(utenteId, req));
    }

	
}
