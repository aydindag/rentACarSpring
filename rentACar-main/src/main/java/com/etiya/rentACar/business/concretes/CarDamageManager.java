package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CarDamageService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.CarDamageMessages;
import com.etiya.rentACar.business.constants.messages.CarMessages;
import com.etiya.rentACar.business.dtos.CarDamageSearchListDto;
import com.etiya.rentACar.business.dtos.CarImageSearchListDto;
import com.etiya.rentACar.business.request.carDamageRequests.CreateCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.rentACar.business.request.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.dataAccess.abstracts.CarDamageDao;
import com.etiya.rentACar.entities.Car;
import com.etiya.rentACar.entities.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CarDamageManager implements CarDamageService {

    private ModelMapperService modelMapperService;
    private CarDamageDao carDamageDao;
    private CarService carService;

    @Autowired
    public CarDamageManager(ModelMapperService modelMapperService, CarDamageDao carDamageDao,CarService carService) {
        this.modelMapperService = modelMapperService;
        this.carDamageDao = carDamageDao;
        this.carService = carService;
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getAllDamages() {
        List<CarDamage> list = this.carDamageDao.findAll();
        List<CarDamageSearchListDto> response = list.stream().map(carDamage -> modelMapperService.forDto().
                map(carDamage, CarDamageSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageSearchListDto>>(response);
    }

    @Override
    public Result save(CreateCarDamageRequest createCarDamageRequest) {
        Result result = BusinessRules.run(checkIfCarExists(createCarDamageRequest.getCarId()));
        if(result!=null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(CarDamageMessages.add);
    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
        Result result = BusinessRules.run(checkIfDamageIdExist(deleteCarDamageRequest.getCarDamageId()));
        if(result!=null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
        this.carDamageDao.delete(carDamage);
        return new SuccessResult(CarDamageMessages.delete);
    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
        Result result = BusinessRules.run(checkIfDamageIdExist(updateCarDamageRequest.getCarDamageId()),
                carService.checkExistingCar(updateCarDamageRequest.getCarId()));
        if(result!=null){
            return result;
        }
        CarDamage carDamage = modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);
        return new SuccessResult(CarDamageMessages.update);
    }

    @Override
    public DataResult<List<CarDamageSearchListDto>> getDamagesByCarId(int carId) {
        Result result = BusinessRules.run(carService.checkExistingCar(carId));
        if(result != null) {
            return new ErrorDataResult<List<CarDamageSearchListDto>>(null,CarMessages.carNotFound);
        }
        List<CarDamage> list = carDamageDao.getByCar_CarId(carId);
        List<CarDamageSearchListDto> response = list.stream().map(carDamage -> modelMapperService.forDto().
                map(carDamage, CarDamageSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarDamageSearchListDto>>(response);
    }

    public Result checkIfCarExists(int carId){
        boolean isExisting = carService.checkExistingCar(carId).isSuccess();
        if(!isExisting){
            return new ErrorResult(CarMessages.carNotFound);
        }
        return new SuccessResult();
    }

    private Result checkIfDamageIdExist(int damageId){
        if(carDamageDao.existsById(damageId)){
            return new SuccessResult();
        }
        return new ErrorResult(CarDamageMessages.carDamageNotFoundId);
    }
}
