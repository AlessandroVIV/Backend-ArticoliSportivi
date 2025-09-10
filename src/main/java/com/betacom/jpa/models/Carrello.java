package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carrello")
@Getter
@Setter
public class Carrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @OneToOne
    @JoinColumn(name = "utente_id", nullable = false, unique = true)
    private Utente utente;
    
    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarrelloItem> articoli;
	
}
