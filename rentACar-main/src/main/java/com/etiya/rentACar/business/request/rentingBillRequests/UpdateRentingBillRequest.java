package com.etiya.rentACar.business.request.rentingBillRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentingBillRequest {
	@NotNull
	private Date creationDate;
	
	@NotNull
	private Date rentingStartDate;
	
	@NotNull
	private Date rentingEndDate;
	
	@NotNull
	private int totalRentingDay;
	
	@NotNull
	private int rentingPrice;
	
	@NotNull
	private int userId;
	
	@NotNull
	private int billId;
}
