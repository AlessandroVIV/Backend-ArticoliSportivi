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
@Table(name = "articoli_ordinati")
@Getter
@Setter
public class ArticoloOrdinato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
