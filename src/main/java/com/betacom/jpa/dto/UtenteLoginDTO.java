package com.betacom.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UtenteLoginDTO {

	private Integer id;
	
	private String userName;
	private String pwd;
	private String nome; 
	private String cognome; 
	private String email;
	private String role;
	
}
