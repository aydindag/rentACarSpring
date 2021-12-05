package com.etiya.rentACar.business.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceSearchListDto {

	private int maintenanceId;
	
	private int carId;
	
	private Date startDate;
	
	private Date endDate;
}
