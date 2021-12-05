package com.etiya.rentACar.business.request.loginAndRegister;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIndividualCustomerRequest {
	
	 @NotBlank
	 @NotNull
	 @Email
	 private String email;
	 
	 @NotBlank
	 @NotNull
	 private String password;
	 
	 @NotBlank
	 @NotNull
	 private String passwordConfirm;
	 
	 @NotBlank
	 @NotNull
	 private String firstName;
	 
	 @NotBlank
	 @NotNull
	 private String lastName;
	 
	 @NotNull
	 private Date birthday;

	 
	 
}
