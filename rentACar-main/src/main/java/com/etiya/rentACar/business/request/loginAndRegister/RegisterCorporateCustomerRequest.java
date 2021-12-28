package com.etiya.rentACar.business.request.loginAndRegister;

import javax.validation.constraints.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCorporateCustomerRequest {
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
	 private String companyName;

	 @NotNull
	 @Size(min = 10, max = 10)
	 @ApiModelProperty(example = "xxxxxxxxxx")
	 private String taxNumber;
	 
}
