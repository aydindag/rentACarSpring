package com.etiya.rentACar.business.request.rentingBillRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentingBillRequest {
	@NotNull
	@JsonIgnore
	private Date creationDate;
	
	@NotNull
	@JsonIgnore
	private Date rentingStartDate;
	
	@NotNull
	@JsonIgnore
	private Date rentingEndDate;
	
	@NotNull
	@JsonIgnore
	private int totalRentingDay;
	
	@NotNull
	@JsonIgnore
	private int rentingPrice;
	
	@NotNull
	@JsonIgnore
	private int userId;
}
