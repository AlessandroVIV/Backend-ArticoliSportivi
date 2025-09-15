package com.betacom.jpa.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.CarrelloDTO;
import com.betacom.jpa.dto.CarrelloItemDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.CarrelloItem;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.ICarrelloItemRepository;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.CarrelloItemReq;
import com.betacom.jpa.services.interfaces.ICarrelloItemInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CarrelloItemImpl implements ICarrelloItemInterfaces {

    @Autowired
    private IUtenteRepository utenteRepository;

    @Autowired
    private IArticoloRepository articoloRepository;

    @Autowired
    private ICarrelloItemRepository carrelloItemRepository;

    @Override
    public CarrelloItemDTO aggiungiItem(Integer utenteId, CarrelloItemReq req) {
    	
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Carrello carrello = utente.getCarrello();

        Articolo articolo = articoloRepository.findById(req.getArticoloId())
                .orElseThrow(() -> new RuntimeException("Articolo non trovato"));

        CarrelloItem item = new CarrelloItem();
        item.setCarrello(carrello);
        item.setArticolo(articolo);
        item.setQuantita(req.getQuantita());
        item.setPrezzoTotale(articolo.getPrezzo() * req.getQuantita());

        CarrelloItem saved = carrelloItemRepository.save(item);

        return toDTO(saved); 
    }

    private CarrelloItemDTO toDTO(CarrelloItem item) {
    	
        CarrelloItemDTO dto = new CarrelloItemDTO();
        dto.setId(item.getId());
        dto.setQuantita(item.getQuantita());
        dto.setPrezzoTotale(item.getPrezzoTotale());

        CarrelloDTO carrelloDTO = new CarrelloDTO();
        carrelloDTO.setId(item.getCarrello().getId());

        Utente utente = item.getCarrello().getUtente();
        
        if (utente != null) {
            UtenteDTO utenteDTO = new UtenteDTO();
            utenteDTO.setId(utente.getId());
            utenteDTO.setNome(utente.getNome());
            utenteDTO.setCognome(utente.getCognome());
            utenteDTO.setUsername(utente.getUsername());
            //utenteDTO.setPassword(utente.getPassword());
            carrelloDTO.setUtente(utenteDTO);
        }

        dto.setCarrello(carrelloDTO);
        dto.setArticolo(item.getArticolo()); 

        return dto;
    }

}

