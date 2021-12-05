package com.etiya.rentACar.business.request.individualCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	@NotNull
	private int userId;
	
	@NotNull
	@Size(min=2,max=16)
	private String firstName;
	
	@NotNull
	@Size(min=2,max=16)
	private String lastName;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String birthday;
}
