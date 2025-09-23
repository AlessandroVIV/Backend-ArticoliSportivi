package com.betacom.jpa.services.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CarrelloDTO;
import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.dto.OrdineDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.CarrelloItem;
import com.betacom.jpa.models.Ordini;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IOrdineRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.OrdineRequest;
import com.betacom.jpa.services.interfaces.IOrdineInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrdineImpl implements IOrdineInterfaces{
	
	@Autowired
	private IUtenteRepository utenteRepository;
	
	@Autowired
	private IOrdineRepository ordineRepository;

	public void createOrdine(OrdineRequest req) {
	    Utente utente = utenteRepository.findById(req.getUtenteId())
	            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

	    Carrello carrello = utente.getCarrello();
	    
	    log.info("carrello size: " + carrello.getArticoli().size());
	    
	    if (carrello == null || carrello.getArticoli() == null || carrello.getArticoli().isEmpty()) {
	        throw new RuntimeException("Carrello vuoto, impossibile creare ordine");
	    }

	    List<CarrelloItem> items = carrello.getArticoli();

	    Ordini ordine = new Ordini();
	    ordine.setUtente(utente);
	    ordine.setDataOrdine(LocalDateTime.now());

	    List<CarrelloItem> ordineItems = new ArrayList<>();
	    for (CarrelloItem item : items) {
	        CarrelloItem ordineItem = new CarrelloItem();
	        ordineItem.setArticolo(item.getArticolo());
	        ordineItem.setQuantita(item.getQuantita());
	        ordineItem.setPrezzoTotale(item.getPrezzoTotale());
	        ordineItem.setOrdine(ordine);
	        ordineItem.setCarrello(carrello);
	        ordineItems.add(ordineItem);
	    }

	    ordine.setItems(ordineItems);
	    
	    carrello.getArticoli().clear();
	    
	    ordineRepository.save(ordine);
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

	    UtenteDTO utenteDTO = new UtenteDTO();
	    utenteDTO.setId(ordine.getUtente().getId());
	    utenteDTO.setNome(ordine.getUtente().getNome());
	    utenteDTO.setCognome(ordine.getUtente().getCognome());
	    utenteDTO.setUsername(ordine.getUtente().getUsername());
	    dto.setUtente(utenteDTO);

	    List<CarrelloItemDTO> itemsDTO = ordine.getItems().stream().map(item -> {
	        CarrelloItemDTO itemDTO = new CarrelloItemDTO();
	        itemDTO.setId(item.getId());
	        itemDTO.setQuantita(item.getQuantita());
	        itemDTO.setPrezzoTotale(item.getPrezzoTotale());
	        itemDTO.setArticolo(item.getArticolo());

	        if (item.getCarrello() != null) {
	            CarrelloDTO carrelloDTO = new CarrelloDTO();
	            carrelloDTO.setId(item.getCarrello().getId());
	            itemDTO.setCarrello(carrelloDTO);
	        }

	        return itemDTO;
	        
	    }).toList();

	    
	    dto.setItems(itemsDTO);

	    return dto;
	}

	@Override
	public OrdineDTO listById(Integer id) throws AcademyException {
	    Ordini ordine = ordineRepository.findById(id)
	            .orElseThrow(() -> new AcademyException("Ordine con id " + id + " non trovato!"));
	    return toDTO(ordine);
	}



	
}
