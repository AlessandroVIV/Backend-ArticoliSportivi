package com.betacom.jpa.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticoloReq {

	private Integer id;
	private String nome;
	private String genere;
	private String marca;
	private String categoria;
    private Double prezzo;
    private String descrizione;
    private Integer tagliaScarpe;
    private String tagliaIndumento;
    private String urlImmagine;
}
