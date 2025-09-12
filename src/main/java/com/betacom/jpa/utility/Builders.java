package com.betacom.jpa.utility;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.models.Articolo;

public class Builders {

	public static List<ArticoloDTO> buildListArticoloDTO(List<Articolo> la) {
	    return la.stream()
	        .map(a -> ArticoloDTO.builder()
	            .id(a.getId())
	            .nomeArticolo(a.getNome())
	            .genere((a.getGenere() != null) ? a.getGenere().getNome() : null)
	            .marca((a.getMarca() != null) ? a.getMarca().getNome() : null)
	            .categoria((a.getCategoria() != null) ? a.getCategoria().getNome() : null)
	            .prezzo(a.getPrezzo())
	            .descrizione(a.getDescrizione())
	            .tagliaScarpe(a.getTagliaScarpe())
	            .tagliaIndumento(a.getTagliaIndumento())
	            .build())
	        .collect(Collectors.toList());
	}

	
}
