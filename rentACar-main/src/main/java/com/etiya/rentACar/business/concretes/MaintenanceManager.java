package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.constants.messages.MaintenanceMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.MaintenanceService;
import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.MaintenanceSearchListDto;
import com.etiya.rentACar.business.request.maintenanceRequests.CreateMaintenanceRequest;
import com.etiya.rentACar.business.request.maintenanceRequests.DeleteMaintenanceRequest;
import com.etiya.rentACar.business.request.maintenanceRequests.UpdateMaintenanceRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.MaintenanceDao;
import com.etiya.rentACar.entities.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService{

	private MaintenanceDao maintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private CarService carService;
	
	@Autowired
	public MaintenanceManager(MaintenanceDao maintenanceDao, ModelMapperService modelMapperService, RentalService rentalService, CarService carService) {
		super();
		this.maintenanceDao = maintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
	}

	@Override
	public DataResult<List<MaintenanceSearchListDto>> getAll() {
		List<Maintenance> result = this.maintenanceDao.findAll();
		List<MaintenanceSearchListDto> response = result.stream().map(maintenance -> modelMapperService.forDto()
				.map(maintenance, MaintenanceSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<MaintenanceSearchListDto>>(response);
	}

	@Override
	public Result save(CreateMaintenanceRequest createMaintenanceRequest) {
		int requestCarId = createMaintenanceRequest.getCarId();
		Result result = BusinessRules.run(checkIfCarIsRentedNow(requestCarId),
				this.carService.checkExistingCar(requestCarId),
				checkIfCarIsOnMaintenance(requestCarId));
		
		if(result != null) {
			return result;
		}
		
		Maintenance maintenance = modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(MaintenanceMessages.add);
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Result result = BusinessRules.run(checkExistingMaintenanceId(deleteMaintenanceRequest.getMaintenanceId()));

		if(result != null) {
			return result;
		}
		Maintenance maintenance = modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.delete(maintenance);
		return new SuccessResult(MaintenanceMessages.delete);
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Result result = BusinessRules.run(checkIfCarIsRentedNow(updateMaintenanceRequest.getCarId()),
				this.carService.checkExistingCar(updateMaintenanceRequest.getCarId()),
				checkExistingMaintenanceId(updateMaintenanceRequest.getMaintenanceId()),
				rentalService.checkIfEndDateIsAfterStartDate(updateMaintenanceRequest.getEndDate(), updateMaintenanceRequest.getStartDate()));
		
		if(result != null) {
			return result;
		}
		Maintenance maintenance = modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceDao.save(maintenance);
		return new SuccessResult(MaintenanceMessages.update);
	}
	
	private Result checkIfCarIsRentedNow(int carId) {
		Result isCarReturned = rentalService.checkCarIsReturned(carId);
		if(!isCarReturned.isSuccess()) {
			return new ErrorResult(MaintenanceMessages.carIsOnRental);
		}
		return new SuccessResult();
	}
	
	public Result checkIfCarIsOnMaintenance(int carId) {
		List<Maintenance> maintenance = maintenanceDao.getByCar_CarId(carId);
		if(maintenance != null) {
			for (Maintenance maintenanceLog : maintenance) {
				if(maintenanceLog.getEndDate() == null) {
					return new ErrorResult(MaintenanceMessages.carIsOnMaintenance);
				}
			}
		}
		return new SuccessResult();
	}
	public Result checkExistingMaintenanceId(int maintenanceId){
		boolean isExists = maintenanceDao.existsByMaintenanceId(maintenanceId);
		if(!isExists){
			return new ErrorResult(MaintenanceMessages.maintenanceIdNotFound);
		}
		return new SuccessResult();
	}

}
