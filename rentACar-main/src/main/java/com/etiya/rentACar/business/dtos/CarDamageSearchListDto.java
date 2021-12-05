package com.etiya.rentACar.business.dtos;

import com.etiya.rentACar.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDamageSearchListDto {

    private int damageId;
    private int carId;
    private String damageDescription;
    private double damageCost;

}
