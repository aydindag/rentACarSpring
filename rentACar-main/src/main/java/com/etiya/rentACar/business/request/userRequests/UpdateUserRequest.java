package com.etiya.rentACar.business.request.userRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	@NotNull
	private int id;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
}
