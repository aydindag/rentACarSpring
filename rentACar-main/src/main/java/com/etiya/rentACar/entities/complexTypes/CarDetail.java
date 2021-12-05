package com.etiya.rentACar.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {
	
	private int carId;
	
	private String brandName;
	
	private String colorName;
	
	private double dailyPrice;
	
	private String modelYear;
	
	private String description;

	private String cityName;

	private int findeksPointCar;

	private int kilometer;
	
//	private List<String> imagePaths;
	
}
