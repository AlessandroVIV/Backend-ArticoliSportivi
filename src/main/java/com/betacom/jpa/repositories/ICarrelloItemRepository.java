package com.betacom.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.CarrelloItem;

public interface ICarrelloItemRepository extends JpaRepository<CarrelloItem, Integer>{

	 Optional<CarrelloItem> findByIdAndCarrelloId(Integer id, Integer carrelloId);
	 
	 List<CarrelloItem> findByCarrelloId(Integer carrelloId);
	 
	 void deleteByArticoloId(Integer articoloId);

}
