package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticoloScarpaReq {
	
	private Integer id;
	private String nomeArticolo;
	private String genere;
	private String marca;
	private String categoria;
    private Double prezzo;
    private String descrizione;
    //private Integer bestSeller;
    private Integer tagliaScarpe;
    private String urlImmagine;
    
}
