package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImageSearchListDto {

	private int imageId;

	private String date;

	private int carId;

	private String imagePath;
}
