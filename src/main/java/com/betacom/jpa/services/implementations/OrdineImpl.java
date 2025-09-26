package com.betacom.jpa.services.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.ArticoloDTO;
import com.betacom.jpa.dto.ArticoloOrdinatoDTO;
import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.ArticoloOrdinato;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.CarrelloItem;
import com.betacom.jpa.models.Ordini;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IArticoloOrdinatoRepository;
import com.betacom.jpa.repositories.ICarrelloRepository;
import com.betacom.jpa.repositories.IOrdineRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.OrdineRequest;
import com.betacom.jpa.services.interfaces.IOrdineInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrdineImpl implements IOrdineInterfaces {

	@Autowired
	private IUtenteRepository utenteRepository;

	@Autowired
	private IOrdineRepository ordineRepository;
	
	@Autowired
	private ICarrelloRepository carrelloRepository;

	@Autowired
	private IArticoloOrdinatoRepository articoloOrdinatoRepository;

	public void createOrdine(OrdineRequest req) {
		Utente utente = utenteRepository.findById(req.getUtenteId())
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		Carrello carrello = utente.getCarrello();

		if (carrello == null || carrello.getArticoli() == null || carrello.getArticoli().isEmpty()) {
			throw new RuntimeException("Carrello vuoto, impossibile creare ordine");
		}

		List<CarrelloItem> items = carrello.getArticoli();

		// Creo l'ordine
		Ordini ordine = new Ordini();
		ordine.setUtente(utente);
		ordine.setDataOrdine(LocalDateTime.now());
		ordine.setIndirizzo(req.getIndirizzo());
		ordine = ordineRepository.save(ordine); // salvo subito per generare l'ID

		// Creo gli articoli ordinati
		for (CarrelloItem item : items) {
			ArticoloOrdinato articoloOrdinato = new ArticoloOrdinato();
			articoloOrdinato.setArticolo(item.getArticolo());
			articoloOrdinato.setQuantita(item.getQuantita());
			articoloOrdinato.setTaglia(item.getTaglia());
			articoloOrdinato.setPrezzoTotale(item.getPrezzoTotale());
			articoloOrdinato.setOrdine(ordine);

			articoloOrdinatoRepository.save(articoloOrdinato);
		}

		// Svuoto il carrello
		carrello.getArticoli().clear();
		carrelloRepository.save(carrello);
	}

	@Override
	public List<OrdineDTO> listAll() {
		List<Ordini> ordini = ordineRepository.findAll();
		return ordini.stream().map(this::toDTO).toList();
	}

	private OrdineDTO toDTO(Ordini ordine) {
	    OrdineDTO dto = new OrdineDTO();
	    dto.setId(ordine.getId());
	    dto.setDataOrdine(ordine.getDataOrdine());

	    // Mapping Utente
	    UtenteDTO utenteDTO = new UtenteDTO();
	    utenteDTO.setId(ordine.getUtente().getId());
	    utenteDTO.setNome(ordine.getUtente().getNome());
	    utenteDTO.setCognome(ordine.getUtente().getCognome());
	    utenteDTO.setUsername(ordine.getUtente().getUsername());
	    dto.setUtente(utenteDTO);

	    // Mapping Articoli Ordinati
	    List<ArticoloOrdinatoDTO> itemsDTO = ordine.getItems().stream()
	        .map(this::toArticoloOrdinatoDTO)
	        .toList();

	    dto.setItems(itemsDTO);
	    return dto;
	}

	private ArticoloOrdinatoDTO toArticoloOrdinatoDTO(ArticoloOrdinato entity) {
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
	        dto.setArticolo(articoloDTO);
	    }

	    return dto;
	}
	
	@Override
	public OrdineDTO listById(Integer id) throws AcademyException {
		Ordini ordine = ordineRepository.findById(id)
				.orElseThrow(() -> new AcademyException("Ordine con id " + id + " non trovato!"));
		return toDTO(ordine);
	}

}
