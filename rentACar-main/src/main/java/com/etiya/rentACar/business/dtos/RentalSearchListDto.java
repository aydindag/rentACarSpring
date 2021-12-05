package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSearchListDto {
	private int rentalId; 
	private int userId;
	private int carId;
	private Date rentDate;
	private Date returnDate;
	private String rentCity;
	private String returnCity;
	private String additionalServices;
	
}
