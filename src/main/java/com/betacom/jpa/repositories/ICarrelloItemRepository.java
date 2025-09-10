package com.betacom.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.CarrelloItem;

public interface ICarrelloItemRepository extends JpaRepository<CarrelloItem, Integer>{

}
