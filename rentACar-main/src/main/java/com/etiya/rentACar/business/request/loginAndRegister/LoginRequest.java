package com.etiya.rentACar.business.request.loginAndRegister;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
}
