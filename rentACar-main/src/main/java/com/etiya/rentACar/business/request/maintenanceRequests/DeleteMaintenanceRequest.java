package com.etiya.rentACar.business.request.maintenanceRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {
	
	@NotNull
	private int maintenanceId;
	
}
