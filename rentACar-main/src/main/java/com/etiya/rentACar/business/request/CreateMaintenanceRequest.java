package com.etiya.rentACar.business.request;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMaintenanceRequest {

	@NotNull
	@Min(1)
	private int carId;
	
	@NotNull
	private Date startDate;
	
	@JsonIgnore
	private Date endDate;
}
