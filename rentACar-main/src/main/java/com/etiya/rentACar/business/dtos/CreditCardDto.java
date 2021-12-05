package com.etiya.rentACar.business.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

	private int cardId;
	
	private String nameOnCard;
	
	private String cardNumber;
	
	private Date expiration;
	
	private String cvc;

	private int userId;
}
