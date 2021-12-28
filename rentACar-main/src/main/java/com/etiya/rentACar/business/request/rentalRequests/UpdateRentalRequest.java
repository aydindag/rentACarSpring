package com.etiya.rentACar.business.request.rentalRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(example = "1970-01-01")
	private Date rentDate;

	@NotNull
	@ApiModelProperty(example = "1970-01-01")
	private Date returnDate;

	@NotNull
	private int rentCity;

	@NotNull
	private int returnCity;

	@NotNull
	private int returnKilometer;

	@ApiModelProperty(example = "1,2")
	private String demandedAdditionalServices;

}
