package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CorporateCustomerService;
import com.etiya.rentACar.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACar.business.request.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.rentACar.business.request.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.rentACar.business.request.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.rentACar.entities.CorporateCustomer;
@Service
public class CorporateCustomerManager implements CorporateCustomerService{

	private ModelMapperService modelMapperService;
	private CorporateCustomerDao corporateCustomerDao;
	
	@Autowired
	public CorporateCustomerManager(ModelMapperService modelMapperService, CorporateCustomerDao corporateCustomerDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.corporateCustomerDao = corporateCustomerDao;
	}

	@Override
	public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
		List<CorporateCustomer> result = corporateCustomerDao.findAll();
		List<CorporateCustomerSearchListDto> response = result.stream().map(corporateCustomer -> modelMapperService.forDto()
				.map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response);
	}

	@Override
	public Result save(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult("Corporate customer added.");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult("Corporate customer deleted.");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult("Corporate customer updated.");
	}

	@Override
	public CorporateCustomer getCustomerByCustomerId(int customerId) {
		CorporateCustomer corporateCustomer = corporateCustomerDao.getById(customerId);
		return corporateCustomer;
	}

}
