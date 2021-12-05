package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	List<Rental> getByCar_CarId(int carId);
}
