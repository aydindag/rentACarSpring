package com.etiya.rentACar.business.request.corporateCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	@NotNull
	private int userId;
	
	@NotNull
	private String companyName;
	
	@NotNull
	@ApiModelProperty(example = "xxxxxxxxxx")
	@Size(min = 10, max = 10)
	private String taxNumber;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
	
}
