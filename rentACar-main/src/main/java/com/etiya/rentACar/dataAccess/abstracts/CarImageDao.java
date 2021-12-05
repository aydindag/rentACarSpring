package com.etiya.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACar.entities.CarImage;

public interface CarImageDao extends JpaRepository<CarImage, Integer>{
	List<CarImage> getByCar_CarId(int id);
}
