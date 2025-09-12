package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ArticoloDTO {

	private Integer id;
	private String nomeArticolo;
	private String genere;
	private String marca;
	private String categoria;
    private Double prezzo;
    private String descrizione;
    //private Integer bestSeller;
    private Integer tagliaScarpe;
    private String tagliaIndumento;
	
}
