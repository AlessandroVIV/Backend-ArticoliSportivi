package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.CarrelloItemReq;

public interface ICarrelloItemInterfaces {
	
	List<CarrelloItemDTO> getItemsByUtente(Integer utenteId);

	CarrelloItemDTO aggiungiItem(Integer utenteId, CarrelloItemReq req);
	
    CarrelloItemDTO aggiornaQuantita(Integer utenteId, Integer itemId, Integer nuovaQuantita);
    
    CarrelloItemDTO aumentaQuantitaSingola(Integer utenteId, Integer itemId);
    
    CarrelloItemDTO diminuisciQuantitaSingola(Integer utenteId, Integer itemId);
	
	void rimuoviItem(Integer utenteId, Integer carrelloItemId) throws AcademyException;
	
}
