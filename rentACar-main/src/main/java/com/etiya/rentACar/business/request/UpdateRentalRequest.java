package com.etiya.rentACar.business.request;

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
	private String rentDate;

	private String returnDate;
	
}
