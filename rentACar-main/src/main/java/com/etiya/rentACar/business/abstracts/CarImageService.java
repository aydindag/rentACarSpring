package com.etiya.rentACar.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.etiya.rentACar.business.dtos.CarImageSearchListDto;
import com.etiya.rentACar.business.request.carImageRequests.CreateCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.DeleteCarImageRequest;
import com.etiya.rentACar.business.request.carImageRequests.UpdateCarImageRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.CarImage;

public interface CarImageService {
	List<CarImageSearchListDto> getCarImages();
	Result save(CreateCarImageRequest createCarImageRequest) throws Exception;
	Result delete(DeleteCarImageRequest deleteCarImageRequest);
	Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException;
	DataResult<List<CarImageSearchListDto>> getCarImageByCarId(int carId);
	List<CarImage> getCarImageListByCarId(int carId);
}
