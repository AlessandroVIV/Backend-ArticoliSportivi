package com.betacom.jpa.requests;

import com.betacom.jpa.utility.Roles;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtenteReq {

	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer idCarrello;
	private Roles role;
	
}
