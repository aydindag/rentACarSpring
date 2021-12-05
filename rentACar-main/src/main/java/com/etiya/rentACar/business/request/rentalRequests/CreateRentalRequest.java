package com.etiya.rentACar.business.request.rentalRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	private Date rentDate;
 
	//private Date returnDate;

	@NotNull
	private String rentCity;

	@NotNull
	private String returnCity;

	@NotNull
	private int carId;
	
	@NotNull
	private int userId;

	@NotNull
	private int rentKilometer;

	private String demandedAdditionalServices;


}
