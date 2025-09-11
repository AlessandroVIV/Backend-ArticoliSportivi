package com.betacom.jpa.dto;

import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Carrello;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarrelloItemDTO {

    private Long id;
    private Carrello carrello;
    private Articolo articolo;
    private Integer quantita;   
    private Double prezzoTotale;
	
}
