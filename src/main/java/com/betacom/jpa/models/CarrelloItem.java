package com.betacom.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carrello_item")
@Getter
@Setter
public class CarrelloItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrello_id", nullable = false)
    private Carrello carrello;
    
    @ManyToOne
    @JoinColumn(name = "articolo_id", nullable = false)
    private Articolo articolo;
    
    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordini ordine;

    private String taglia;
    
    private Integer quantita;
    
    private Double prezzoTotale;

}
