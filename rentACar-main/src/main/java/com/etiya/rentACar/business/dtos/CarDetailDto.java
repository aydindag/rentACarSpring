package com.etiya.rentACar.business.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto {
	
	private int carId;
	private String brandName;
	private String colorName;
	private double dailyPrice;
	private String modelYear;
	private List<String> imagePaths;
	private String cityName;
	private int kilometer;
	private List<String> DamageDescriptions;
}
