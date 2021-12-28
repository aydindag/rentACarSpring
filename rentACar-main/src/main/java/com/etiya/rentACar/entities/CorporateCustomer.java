package com.etiya.rentACar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name="corporate_customers")
public class CorporateCustomer extends User {

	@Column(name="company_name")
	private String companyName;
	
	@Column(name="tax_number", unique = true)
	private String taxNumber;
}
