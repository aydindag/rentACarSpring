package com.etiya.rentACar.business.request.carImageRequests;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	@NotNull
	private int imageId;
	@NotNull
	private int carId;
	@NotNull
	private String imagePath;
	@NotNull
	private MultipartFile multipartFile;
}
