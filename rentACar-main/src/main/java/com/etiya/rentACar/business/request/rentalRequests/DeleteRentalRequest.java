package com.etiya.rentACar.business.request.rentalRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalRequest {
	@NotNull
	private int rentalId;
}
