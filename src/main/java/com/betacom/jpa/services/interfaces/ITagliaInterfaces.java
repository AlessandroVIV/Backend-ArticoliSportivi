package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.TagliaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.requests.TagliaRequest;

public interface ITagliaInterfaces {

	void createTaglia(TagliaRequest req) throws AcademyException;
	
	void updateTaglia(TagliaRequest req) throws AcademyException;

	void deleteTaglia(TagliaRequest req) throws AcademyException;
	
	List<TagliaDTO> listAll();
	
}
