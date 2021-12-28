package com.etiya.rentACar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name="individual_customers")
public class IndividualCustomer extends User {
	
	@Column(name="first_name")  
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="birthday")
	private Date birthday;

//	@OneToMany(mappedBy="individualCustomer")
//	private List<Rental> rentals; 
	
}
