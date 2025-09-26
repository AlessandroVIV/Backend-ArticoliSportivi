package com.betacom.jpa.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class OrdineDTO {
    private Integer id;
    private UtenteDTO utente;
    private List<ArticoloOrdinatoDTO> items;
    private LocalDateTime dataOrdine;
    private String indirizzo;
}
