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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="maintenances")
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="maintenance_id")
	private int maintenanceId;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="finish_date")
	private Date endDate;
	
}
