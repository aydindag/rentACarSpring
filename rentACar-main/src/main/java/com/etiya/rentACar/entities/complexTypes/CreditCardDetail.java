package com.etiya.rentACar.entities.complexTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDetail {

    private String nameOnCard;

    private String cardNumber;

    private Date expiration;

    private String cvc;
}
