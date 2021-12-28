package com.etiya.rentACar.business.request.maintenanceRequests;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(example = "1970-01-01")
	private Date startDate;
	
	@NotNull
	@ApiModelProperty(example = "1970-01-01")
	private Date endDate;
}
