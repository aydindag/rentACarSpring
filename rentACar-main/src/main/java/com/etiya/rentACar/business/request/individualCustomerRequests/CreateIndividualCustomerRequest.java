package com.etiya.rentACar.business.request.individualCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

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
	@ApiModelProperty(example = "1970-01-01")
	private Date birthday;

}
