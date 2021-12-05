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
@Table(name="car_images")
public class CarImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="image_id")
	private int imageId;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@Column(name="image_path")
	private String imagePath;

}
