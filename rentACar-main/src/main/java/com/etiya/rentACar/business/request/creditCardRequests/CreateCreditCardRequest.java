package com.etiya.rentACar.business.request.creditCardRequests;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {
	
	@NotNull
	private String nameOnCard;
	
	@NotNull
	private String cardNumber;
	
	@NotNull
	private Date expiration;
	
	@NotNull
	@Size(min = 3, max = 3)
	private String cvc;

	@NotNull
	private int userId;
}
