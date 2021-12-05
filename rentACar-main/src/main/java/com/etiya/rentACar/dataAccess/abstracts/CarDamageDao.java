package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {
    List<CarDamage> getByCar_CarId(int carId);
}
