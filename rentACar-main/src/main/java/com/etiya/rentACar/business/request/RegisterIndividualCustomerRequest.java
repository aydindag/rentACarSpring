package com.etiya.rentACar.business.request;

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
	 private String email;
	 
	 @NotBlank
	 @NotNull
	 private String password;
	 
	 @NotBlank
	 @NotNull
	 private String passwordConfirm;

}
