package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.LoginDTO;
import com.betacom.jpa.dto.UtenteDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Carrello;
import com.betacom.jpa.models.Utente;
import com.betacom.jpa.repositories.IUtenteRepository;
import com.betacom.jpa.requests.LoginReq;
import com.betacom.jpa.requests.UtenteReq;
import com.betacom.jpa.services.interfaces.IUtenteInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UtenteImpl implements IUtenteInterfaces {

	@Autowired
	IUtenteRepository utenteRepository;

	@Override
	public Utente createUtente(UtenteReq req) throws AcademyException {

		log.debug("Create Utente: " + req);

		if (utenteRepository.findByUsername(req.getUsername()).isPresent()) {
			throw new AcademyException("Username già registrato!");
		}

		Utente utente = new Utente();
		utente.setNome(req.getNome());
		utente.setCognome(req.getCognome());
		utente.setUsername(req.getUsername());
		utente.setPassword(req.getPassword());
		utente.setEmail(req.getEmail());
		utente.setRole(req.getRole());

		Carrello carrello = new Carrello();

		utente.setCarrello(carrello);
		carrello.setUtente(utente);

		return utenteRepository.save(utente);

	}

	@Override
	public Utente updateUtente(UtenteReq req) throws AcademyException {

		log.debug("Update utente: " + req);

		Utente utente = utenteRepository.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Utente non trovato nel database con id: " + req.getId()));

		if (req.getNome() != null)
			utente.setNome(req.getNome());
		if (req.getCognome() != null)
			utente.setCognome(req.getCognome());
		if (req.getUsername() != null)
			utente.setUsername(req.getUsername());
		if (req.getPassword() != null)
			utente.setPassword(req.getPassword());
		if (req.getEmail() != null)
			utente.setEmail(req.getEmail());
		if (req.getRole() != null)
			utente.setRole(req.getRole());

		return utenteRepository.save(utente);

	}

	@Override
	public List<UtenteDTO> listAll() {

		List<Utente> lc = utenteRepository.findAll();

		return lc.stream().map(c -> UtenteDTO.builder().id(c.getId()).nome(c.getNome()).cognome(c.getCognome())
				.username(c.getUsername()).build()).collect(Collectors.toList());
	}

	@Override
	public Utente deleteUtente(UtenteReq req) throws AcademyException {

		log.debug("Delete utente con id: " + req.getId());

		Utente utente = utenteRepository.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Utente non trovato nel database con id: " + req.getId()));

		utenteRepository.delete(utente);

		return utente;

	}

	@Override
	public LoginDTO login(LoginReq req) {

		log.debug("login :" + req);

		LoginDTO r = new LoginDTO();
		Optional<Utente> u = utenteRepository.findByUsernameAndPassword(req.getUser(), req.getPassword());

		if (u.isEmpty()) {
			r.setLogged(false);
		} else {
			r.setId(u.get().getId());
			r.setLogged(true);
			r.setRole(u.get().getRole().toString());
		}
		return r;
	}

}
