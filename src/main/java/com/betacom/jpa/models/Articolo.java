package com.betacom.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "articoli")
@Getter
@Setter
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "genere_id", nullable = false)
    private Genere genere;
    
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    private Double prezzo;
    
    private String descrizione;
    
    //private Integer bestSeller;

    @Min(30)
    @Max(50)
    private Integer tagliaScarpe;
    
    private String tagliaIndumento;
    
    private String urlImmagine;

}
