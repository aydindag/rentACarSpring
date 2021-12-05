package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarSearchListDto {

	private int carId;
	private int brandId;
	private int colorId;
	private String modelYear;
	private double dailyPrice;
	private int findeksPointCar;
	private String cityName;
	private int kilometer;

}