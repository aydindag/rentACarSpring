package com.etiya.rentACar.business.request.rentalRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import lombok.*;
import org.springframework.context.annotation.Description;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	@ApiModelProperty(example = "1970-01-01")
	private Date rentDate;
 
	//private Date returnDate;

	@NotNull
	private int rentCity;

	@NotNull
	private int returnCity;

	@NotNull
	private int carId;
	
	@NotNull
	private int userId;

	@NotNull
	private int rentKilometer;

	@ApiModelProperty(example = "1,2")
	private String demandedAdditionalServices;


}
