package com.betacom.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticoloIndumentoDTO {

	private Integer id;
	private String nomeArticolo;
	private String genere;
	private String marca;
	private String categoria;
    private Double prezzo;
    private String descrizione;
    private Integer bestSeller;
    private Integer tagliaIndumento;
    private String urlImmagine;
    
}
