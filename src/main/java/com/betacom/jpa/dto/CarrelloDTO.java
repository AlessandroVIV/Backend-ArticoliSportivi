package com.betacom.jpa.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarrelloDTO {

	private Integer id;
    private UtenteDTO utente;
    private List<CarrelloItemDTO> articoli;
}
