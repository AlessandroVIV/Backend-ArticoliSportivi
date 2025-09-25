package com.betacom.jpa.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.dto.ArticoloOrdinatoDTO;
import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.ArticoloOrdinato;
import com.betacom.jpa.models.Ordini;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IArticoloOrdinatoRepository;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.IOrdineRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.ArticoloOrdinatoReq;
import com.betacom.jpa.services.interfaces.IArticoloOrdinatoInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ArticoloOrdinatoImpl implements IArticoloOrdinatoInterfaces{
	
	@Autowired
    private IUtenteRepository utenteRepository;
	
	@Autowired
    private IOrdineRepository ordineRepository;

    @Autowired
    private IArticoloRepository articoloRepository;
    
    @Autowired
    private IArticoloOrdinatoRepository articoloOrdinatoRepository;
	
    @Override
    public ArticoloOrdinatoDTO createArticoloOrdinato(Integer utenteId, ArticoloOrdinatoReq req) throws AcademyException {
        // Recupera l'utente
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // Recupera l'articolo
        Articolo articolo = articoloRepository.findById(req.getArticoloId())
                .orElseThrow(() -> new RuntimeException("Articolo non trovato"));

        // Cerca un ordine aperto dell'utente (dataOrdine == null)
        Ordini ordine = articoloOrdinatoRepository.findOrdineApertoPerUtente(utenteId)
                .orElse(null);

        // Se non c'è ordine aperto, lo creiamo
        if (ordine == null) {
            ordine = new Ordini();
            ordine.setUtente(utente);
            ordine.setDataOrdine(null); // ordine aperto
            // Salviamo subito per generare ID
            ordine = ordineRepository.save(ordine);
        }

        // Crea l'articolo ordinato
        ArticoloOrdinato articoloOrdinato = new ArticoloOrdinato();
        articoloOrdinato.setArticolo(articolo);
        articoloOrdinato.setOrdine(ordine);
        articoloOrdinato.setQuantita(req.getQuantita());
        articoloOrdinato.setTaglia(req.getTaglia());
        articoloOrdinato.setPrezzoTotale(articolo.getPrezzo() * req.getQuantita());

        // Salva l'articolo ordinato
        ArticoloOrdinato saved = articoloOrdinatoRepository.save(articoloOrdinato);

        // Mapping su DTO
        return toDTO(saved);
    }

	
	private ArticoloOrdinatoDTO toDTO(ArticoloOrdinato entity) {
	    
	    ArticoloOrdinatoDTO dto = new ArticoloOrdinatoDTO();
	    dto.setId(entity.getId());
	    dto.setTaglia(entity.getTaglia());
	    dto.setQuantita(entity.getQuantita());
	    dto.setPrezzoTotale(entity.getPrezzoTotale());

	    // Mapping dell'articolo
	    if (entity.getArticolo() != null) {
	        ArticoloDTO articoloDTO = new ArticoloDTO();
	        articoloDTO.setId(entity.getArticolo().getId());
	        articoloDTO.setNomeArticolo(entity.getArticolo().getNome()); 
	        articoloDTO.setDescrizione(entity.getArticolo().getDescrizione());
	        articoloDTO.setPrezzo(entity.getArticolo().getPrezzo());
	        // ... aggiungi gli altri campi che hai in ArticoloDTO
	        dto.setArticolo(articoloDTO);
	    }

	    // Mapping dell'ordine
	    if (entity.getOrdine() != null) {
	        OrdineDTO ordineDTO = new OrdineDTO();
	        ordineDTO.setId(entity.getOrdine().getId());
	        ordineDTO.setDataOrdine(entity.getOrdine().getDataOrdine());
	        dto.setOrdine(ordineDTO);
	    }

	    return dto;
	}

}
