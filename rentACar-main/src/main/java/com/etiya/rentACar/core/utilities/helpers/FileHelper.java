package com.etiya.rentACar.core.utilities.helpers;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.etiya.rentACar.core.utilities.results.Result;

public interface FileHelper {
	Result saveImage(int carId, MultipartFile multipartFile) throws IOException;
	Result deleteImage(String imagePath);
	Result updateImage(MultipartFile multipartFile, String imagePath);
	Result returnFilePath(int carId);
}
