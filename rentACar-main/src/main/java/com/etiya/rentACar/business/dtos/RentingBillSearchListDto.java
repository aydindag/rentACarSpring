package com.etiya.rentACar.business.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentingBillSearchListDto {
	
	private int billId;
	private Date creationDate;
	private Date rentingStartDate;
	private Date rentingEndDate;
	private int totalRentingDay;
	private int rentingPrice;
	private int userId;
	
}
 