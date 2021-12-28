package com.etiya.rentACar.business.concretes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.constants.messages.RentalMessages;
import com.etiya.rentACar.business.constants.messages.RentingBillMessages;
import com.etiya.rentACar.business.constants.messages.UserMessages;
import com.etiya.rentACar.business.request.rentingBillRequests.UpdateRentingBillRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.results.*;
import com.etiya.rentACar.entities.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.abstracts.RentingBillService;
import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.dtos.RentingBillSearchListDto;
import com.etiya.rentACar.business.request.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.request.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.business.request.rentingBillRequests.DeleteRentingBillRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.dataAccess.abstracts.RentingBillDao;
import com.etiya.rentACar.entities.RentingBill;

@Service
public class RentingBillManager implements RentingBillService {

	private RentingBillDao rentingBillDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private CarService carService;
	private RentalService rentalService;
	@Autowired
	public RentingBillManager(RentingBillDao rentingBillDao, ModelMapperService modelMapperService,
							  UserService userService, CarService carService, @Lazy RentalService rentalService) {
		super();
		this.rentingBillDao = rentingBillDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
		this.carService = carService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<RentingBillSearchListDto>> getAll() {
		List<RentingBill> result = rentingBillDao.findAll();
		List<RentingBillSearchListDto> response = result.stream().map(rentingBill -> modelMapperService.forDto().
				map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
	}

	@Override
	public Result save(UpdateRentalRequest updateRentalRequest) {

		RentingBill rentingBill = new RentingBill();
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		rentingBill.setCreationDate(dateNow);
		rentingBill.setRentingStartDate(updateRentalRequest.getRentDate());
		rentingBill.setRentingEndDate(updateRentalRequest.getReturnDate());
		rentingBill.setUser(userService.getById(updateRentalRequest.getUserId()));
		int totalRentDay = calculateDifferenceBetweenDays(updateRentalRequest.getReturnDate(), updateRentalRequest.getRentDate());
		rentingBill.setTotalRentingDay(totalRentDay);
		int dailyPriceOfCar = (int)(carService.getCarDetailsByCarId(updateRentalRequest.getCarId()).getData().getDailyPrice());
		rentingBill.setRentingPrice(calculateRentingPrice(updateRentalRequest.getRentCity(),
				updateRentalRequest.getReturnCity(),
				dailyPriceOfCar,totalRentDay,updateRentalRequest));
		rentingBillDao.save(rentingBill);
		return new SuccessResult(RentingBillMessages.add);
	}

	@Override
	public Result delete(DeleteRentingBillRequest deleteRentingBillRequest) {
		RentingBill rentingBill = modelMapperService.forRequest().map(deleteRentingBillRequest, RentingBill.class);
		this.rentingBillDao.delete(rentingBill);
		return new SuccessResult(RentingBillMessages.delete);
	}

	@Override
	public Result update(UpdateRentingBillRequest updateRentingBillRequest) {
		RentingBill rentingBill = modelMapperService.forRequest().map(updateRentingBillRequest, RentingBill.class);
		this.rentingBillDao.save(rentingBill);
		return new SuccessResult(RentingBillMessages.update);
	}
	
	private int calculateDifferenceBetweenDays(Date endDate, Date startDate) {
		long difference = (endDate.getTime() - startDate.getTime())/86400000;
		return Math.abs((int)difference);
	}

	@Override
	public DataResult<List<RentingBillSearchListDto>> getRentingBillByUserId(int userId) {
		Result result = BusinessRules.run(userService.existsById(userId));
		if (result != null){
			return new ErrorDataResult<List<RentingBillSearchListDto>>(null, UserMessages.userDoesNotExist);
		}
		List<RentingBill> list = rentingBillDao.getByUser_UserId(userId);
		List<RentingBillSearchListDto> response = list.stream().map(rentingBill -> modelMapperService.forDto().
				map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
	}

	@Override
	public DataResult<List<RentingBillSearchListDto>> getRentingBillByDateInterval(Date startDate, Date endDate) {
		Result result = BusinessRules.run(rentalService.checkIfEndDateIsAfterStartDate(endDate,startDate));
		if (result != null){
			return new ErrorDataResult<List<RentingBillSearchListDto>>(null, RentalMessages.dateAccordance);
		}
		List<RentingBill> list = rentingBillDao.findByCreationDateBetween(startDate, endDate);
		List<RentingBillSearchListDto> response = list.stream().map(rentingBill -> modelMapperService.forDto().
				map(rentingBill, RentingBillSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<RentingBillSearchListDto>>(response);
	}

	private int calculateRentingPrice(int rentCity, int returnCity, int dailyPriceOfCar,
									  int totalRentDay, UpdateRentalRequest updateRentalRequest){

		List<AdditionalService> list = new ArrayList<>();
		if (rentalService.extractAdditionalServicesFromString(updateRentalRequest) != null){
			list = rentalService.extractAdditionalServicesFromString(updateRentalRequest).getData();
		}

		if (list == null){
			if (rentCity != (returnCity)){
				int price = (dailyPriceOfCar*totalRentDay) + 500;
				return price;
			}
			int price = dailyPriceOfCar*totalRentDay;
			return price;
		}
		int totalAdditionalServiceCost=0;
		for (AdditionalService service : list) {
				totalAdditionalServiceCost += service.getServiceDailyPrice();
		}

		if (rentCity != (returnCity)){
			int price = (dailyPriceOfCar*totalRentDay) + 500;
			price += totalAdditionalServiceCost * totalRentDay;
			return price;
		}
		int price = dailyPriceOfCar*totalRentDay;
		price += totalAdditionalServiceCost * totalRentDay;
		return price;

	}

}
