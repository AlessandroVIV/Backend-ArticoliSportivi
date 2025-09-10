package com.betacom.jpa.models;

import com.betacom.jpa.utility.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	
    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carrello carrello;

	@Enumerated(EnumType.STRING)
	private Roles role;

}