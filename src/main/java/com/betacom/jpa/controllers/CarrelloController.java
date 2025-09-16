package com.betacom.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @DeleteMapping("/{utenteId}/items/{carrelloItemId}")
    public ResponseEntity<?> rimuoviItem(
            @PathVariable Integer utenteId,
            @PathVariable Integer carrelloItemId) {
        try {
            carrelloItemInterfaces.rimuoviItem(utenteId, carrelloItemId);
            return ResponseEntity.ok("Item rimosso con successo");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{utenteId}/items/{itemId}")
    public ResponseEntity<CarrelloItemDTO> aggiornaQuantita(
            @PathVariable Integer utenteId,
            @PathVariable Integer itemId,
            @RequestParam Integer quantita) {

        CarrelloItemDTO updated = carrelloItemInterfaces.aggiornaQuantita(utenteId, itemId, quantita);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{utenteId}/items/{itemId}/incrementa")
    public ResponseEntity<CarrelloItemDTO> incrementaQuantita(
            @PathVariable Integer utenteId,
            @PathVariable Integer itemId) {

        return ResponseEntity.ok(carrelloItemInterfaces.aumentaQuantitaSingola(utenteId, itemId));
    }
    
    @PatchMapping("/{utenteId}/items/{itemId}/decrementa")
    public ResponseEntity<CarrelloItemDTO> decrementaQuantita(
            @PathVariable Integer utenteId,
            @PathVariable Integer itemId) {

        return ResponseEntity.ok(carrelloItemInterfaces.diminuisciQuantitaSingola(utenteId, itemId));
    }

}
