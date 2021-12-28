package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.rentACar.business.request.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.rentACar.business.request.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.rentACar.business.request.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.CorporateCustomer;

public interface CorporateCustomerService {
	DataResult<List<CorporateCustomerSearchListDto>> getAll();
	Result save(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	CorporateCustomer getCustomerByCustomerId(int customerId);
	Result checkIfTaxNumberIsNumeric(String taxNumber);
}
