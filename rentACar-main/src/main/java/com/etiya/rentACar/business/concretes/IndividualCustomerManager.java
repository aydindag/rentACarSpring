package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.IndividualCustomerService;
import com.etiya.rentACar.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACar.business.request.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.rentACar.business.request.individualCustomerRequests.DeleteIndividualCustomerRequest;
import com.etiya.rentACar.business.request.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.rentACar.entities.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		List<IndividualCustomer> result = this.individualCustomerDao.findAll();
		List<IndividualCustomerSearchListDto> response = result.stream().map(individualCustomer -> modelMapperService.forDto()
				.map(individualCustomer, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response);
	}

	@Override
	public Result save(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
//		int max=1900;
//		int min=0;
//		int range = max - min + 1;
//		int randomNumberInt = (int)(Math.random() * range) + min;

		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult("Individual customer added.");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult("Individual customer deleted.");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult("Individual customer updated.");
	}
	
//	private Result checkUserIdDuplication(int userId) {
//		IndividualCustomer individualCustomer = this.individualCustomerDao.getByUser_UserId(userId);
//		if(individualCustomer != null) {
//			return new ErrorResult("Kullanıcı numarası tekrarlayamaz.");
//		}
//		return new SuccessResult();
//	}
//	
//	private Result checkIfUserExist(int userId) {
//		for (User users : userDao.findAll()) {
//			if(users.getUserId() == userId) {
//				return new SuccessResult();
//			}
//		}
//		return new ErrorResult("Kullanıcı bulunamadı.");
//	}

	@Override
	public IndividualCustomer getCustomerByCustomerId(int customerId) {
		IndividualCustomer individualCustomer = individualCustomerDao.getById(customerId);
		return individualCustomer;
	}
}
