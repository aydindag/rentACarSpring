package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACar.business.request.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.business.request.carDamageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<CarDamageSearchListDto>> getAllDamages();
    Result save(CreateCarDamageRequest createCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    DataResult<List<CarDamageSearchListDto>> getDamagesByCarId(int carId);
}
