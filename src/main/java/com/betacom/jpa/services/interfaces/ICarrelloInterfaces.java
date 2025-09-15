package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.models.CarrelloItem;
import com.betacom.jpa.requests.CarrelloItemReq;

public interface ICarrelloInterfaces {

	CarrelloItem aggiungiItem(Integer utenteId, CarrelloItemReq req);

}
