package com.betacom.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticoloOrdinatoDTO {
    private Integer id;
    private ArticoloDTO articolo;
    private OrdineDTO ordine;
    private String taglia;
    private Integer quantita;
    private Double prezzoTotale;
}
