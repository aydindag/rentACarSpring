package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.request.loginAndRegister.LoginRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterCorporateCustomerRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface LoginService {

	Result login(LoginRequest loginRequest);
	Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest);
	Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest);
}
