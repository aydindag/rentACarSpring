package com.etiya.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {

	private String firstName;
	private String lastName;
	private String birthday;
	private int userId;
}
 