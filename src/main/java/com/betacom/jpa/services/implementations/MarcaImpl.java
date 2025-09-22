package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.MarcaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Articolo;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repositories.IArticoloRepository;
import com.betacom.jpa.repositories.IMarcaRepository;
import com.betacom.jpa.requests.MarcaReq;
import com.betacom.jpa.response.ResponseBase;
import com.betacom.jpa.services.interfaces.IMarcaInterfaces;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl implements IMarcaInterfaces {

    @Autowired
    private IMarcaRepository marcaRepository;

    @Autowired
    private IArticoloRepository articoloRepository;

    @Override
    public List<MarcaDTO> listAll() {
  
        List<Marca> lc = marcaRepository.findAll();
        
        log.debug("Lista marche: ", lc);

        return lc.stream()
                .map(c -> MarcaDTO.builder()
                        .id(c.getId())
                        .nome(c.getNome())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseBase createMarca(MarcaReq req) {
        log.debug("Create Marca: " + req);
        ResponseBase response = new ResponseBase();

        try {
            if (marcaRepository.findByNome(req.getNome()).isPresent()) {
                throw new AcademyException("Marca già presente nel database!");
            }

            Marca mar = new Marca();
            mar.setNome(req.getNome());
            marcaRepository.save(mar);

            response.setRc(true);
            response.setMsg("Marca creata con successo");
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg(e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseBase updateMarca(MarcaReq req) {
        log.debug("Update Marca: " + req);
        ResponseBase response = new ResponseBase();

        try {
            Marca mar = marcaRepository.findById(req.getId())
                    .orElseThrow(() -> new AcademyException(
                            "Marca non trovata nel database con id: " + req.getId()));

            if (req.getNome() != null && !req.getNome().equals(mar.getNome())) {
                if (marcaRepository.findByNome(req.getNome()).isPresent()) {
                    throw new AcademyException("Marca già presente nel database!");
                }
                mar.setNome(req.getNome());
            }

            marcaRepository.save(mar);

            response.setRc(true);
            response.setMsg("Marca aggiornata con successo");
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg(e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseBase deleteMarca(MarcaReq req) {
        log.debug("Delete Marca con id: " + req.getId());
        ResponseBase response = new ResponseBase();

        try {
            Optional<Marca> mar = marcaRepository.findByNome(req.getNome());

            if (mar.isEmpty()) {
                throw new AcademyException("Marca non trovata con id:" + req.getId());
            }

            List<Articolo> articoliMarca = articoloRepository.findByMarca_Nome(req.getNome());
            if (!articoliMarca.isEmpty()) {
                throw new AcademyException("Marca presente in articoli, impossibile cancellare");
            }

            marcaRepository.delete(mar.get());

            response.setRc(true);
            response.setMsg("Marca eliminata con successo");
        } catch (Exception e) {
            response.setRc(false);
            response.setMsg(e.getMessage());
        }

        return response;
    }
}
