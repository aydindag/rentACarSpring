package com.etiya.rentACar.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="credit_cards")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int cardId;
	
	@Column(name="card_name")
	private String nameOnCard;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expirationDate")
	private Date expiration;
	
	@Column(name="cvc")
	private String cvc;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;
}
