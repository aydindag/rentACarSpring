package com.etiya.rentACar.business.request.carImageRequests;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImageRequest {
	@JsonIgnore
	private int imageId;
	
	@NotNull
	private int carId;
	
	@JsonIgnore
	private MultipartFile multipartFile;
	

}
