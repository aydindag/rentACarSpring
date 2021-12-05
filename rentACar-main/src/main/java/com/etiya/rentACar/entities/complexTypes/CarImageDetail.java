package com.etiya.rentACar.entities.complexTypes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageDetail {
	
	private int carId;
	
	private String brandName;
	
	private String colorName;
	
	private double dailyPrice;
	
	private String modelYear;
	
	private String description;
	
	private List<String> imagePaths;
}
