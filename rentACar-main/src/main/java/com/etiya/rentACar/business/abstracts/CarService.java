package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.CarDetailDto;
import com.etiya.rentACar.business.dtos.CarSearchListDto;
import com.etiya.rentACar.business.request.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.request.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.request.carRequests.UpdateCarRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.complexTypes.CarDetail;

public interface CarService {
	List<CarSearchListDto> getCars();
	Result save(CreateCarRequest createCarRequest);
	Result delete(DeleteCarRequest deleteCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	DataResult<List<CarDetail>> getCarWithColorAndBrandDetails();
	DataResult<List<CarSearchListDto>> getByBrandId(int brandId);
	DataResult<List<CarSearchListDto>> getByColorId(int colorId);
	DataResult<List<CarSearchListDto>> getByCity(int cityId);
	DataResult<CarDetailDto> getCarDetailsByCarId(int carId);
	Car getCarAsElementByCarId(int carId);
	void updateCarCity(int carId, int cityId);
	void updateCarKilometer(int carId, int kilometer);
	Result checkExistingCar(int carId);
	DataResult<List<CarDetail>> getCarsThatAreNotOnMaintenance();
}
