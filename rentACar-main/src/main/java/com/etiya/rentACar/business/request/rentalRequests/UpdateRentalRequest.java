package com.etiya.rentACar.business.request.rentalRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	@NotNull
	private int rentalId;
	
	@NotNull
	private int carId;
	
	@NotNull
	private int userId;
	
	@NotNull
	private Date rentDate;

	@NotNull
	private Date returnDate;

	@NotNull
	private String rentCity;

	@NotNull
	private String returnCity;

	@NotNull
	private int returnKilometer;

	private String demandedAdditionalServices;

}
