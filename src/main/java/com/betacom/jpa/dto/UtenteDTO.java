package com.betacom.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteDTO {

	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private CarrelloDTO carrello;
}
