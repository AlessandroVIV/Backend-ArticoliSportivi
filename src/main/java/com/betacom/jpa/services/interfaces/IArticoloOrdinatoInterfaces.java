package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.dto.ArticoloOrdinatoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.ArticoloOrdinatoReq;

public interface IArticoloOrdinatoInterfaces {

	ArticoloOrdinatoDTO createArticoloOrdinato (Integer utenteId, ArticoloOrdinatoReq req) throws AcademyException;
}
