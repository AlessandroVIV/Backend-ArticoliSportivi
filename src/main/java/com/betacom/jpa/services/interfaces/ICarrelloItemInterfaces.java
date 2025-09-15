package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.requests.CarrelloItemReq;

public interface ICarrelloItemInterfaces {

	CarrelloItemDTO aggiungiItem(Integer utenteId, CarrelloItemReq req);
	
}
