package com.etiya.rentACar.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	@Column(name="rental_id")
	private int rentalId;
	
	@Column(name="rent_date")
	private Date rentDate;
	
	//@Column(name="individual_id")
	//private int individualId;

	@Column(name="return_date") 
	private Date returnDate;

	@Column(name="rent_city")
	private String rentCity;

	@Column(name="return_city")
	private String returnCity;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne (cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "demanded_additional_services")
	private String demandedAdditionalServices;
}


