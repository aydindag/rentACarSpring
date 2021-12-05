package com.etiya.rentACar.business.request.maintenanceRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateMaintenanceRequest {
	
	@NotNull
	private int maintenanceId;
	
	@NotNull
	private int carId;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
}
