package com.betacom.jpa.dto;

import com.betacom.jpa.models.Articolo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarrelloItemDTO {

    private Long id;
    private CarrelloDTO carrello;
    private Articolo articolo;
    private Integer quantita;   
    private Double prezzoTotale;
    private String taglia;
	
}
