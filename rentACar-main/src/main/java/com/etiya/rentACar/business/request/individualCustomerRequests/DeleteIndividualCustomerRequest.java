package com.etiya.rentACar.business.request.individualCustomerRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIndividualCustomerRequest {
	@NotNull
	private int userId;
}
