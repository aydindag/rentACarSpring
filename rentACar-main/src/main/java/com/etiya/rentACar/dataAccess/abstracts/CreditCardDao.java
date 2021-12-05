package com.etiya.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {

}
