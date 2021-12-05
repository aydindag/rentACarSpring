package com.etiya.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CorporateCustomerService;
import com.etiya.rentACar.business.abstracts.IndividualCustomerService;
import com.etiya.rentACar.business.abstracts.LoginService;
import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.request.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.rentACar.business.request.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.rentACar.business.request.loginAndRegister.LoginRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterCorporateCustomerRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;

@Service
public class LoginManager implements LoginService {
	
	private UserService userService;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	
	public LoginManager(UserService userService, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
		super();
		this.userService = userService;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		Result result = BusinessRules.run(this.checkCustomerByEmail(loginRequest),
				this.checkCustomerByPassword(loginRequest));
		if (result != null) {
			return result;
		}
		return new SuccessResult("Successfuly login");
	}

	@Override
	public Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		Result resultCheck = BusinessRules.run(checkPasswordConfirmationForIndividualCustomerRegistration(registerIndividualCustomerRequest));
		if(resultCheck != null) {
			return resultCheck;
		}
		CreateIndividualCustomerRequest result = modelMapperService.forRequest()
				.map(registerIndividualCustomerRequest, CreateIndividualCustomerRequest.class);
		this.individualCustomerService.save(result);
		return new SuccessResult("Customer added");
	}
	
	private Result checkCustomerByPassword(LoginRequest loginRequest) {

		if (checkCustomerByEmail(loginRequest).isSuccess()) {

			if (!this.userService.getByEmail(loginRequest.getEmail()).getData().getPassword()
					.equals(loginRequest.getPassword())) {
				return new ErrorResult("Wrong password");
			}
		}
		return new SuccessResult();
	}
	
	private Result checkCustomerByEmail(LoginRequest loginRequest) {

		if (this.userService.existsByEmail(loginRequest.getEmail()).isSuccess()) {
			return new ErrorResult("Wrong e-mail");
		}
		return new SuccessResult();
	}
	
	private Result checkPasswordConfirmationForIndividualCustomerRegistration(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		if(!(registerIndividualCustomerRequest.getPassword()).equals(registerIndividualCustomerRequest.getPasswordConfirm())) {
			return new ErrorResult("Password does not match");
		}
		return new SuccessResult();
	}
	private Result checkPasswordConfirmationForCorporateCustomerRegistration(RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		if(!(registerCorporateCustomerRequest.getPassword()).equals(registerCorporateCustomerRequest.getPasswordConfirm())) {
			return new ErrorResult("Password does not match");
		}
		return new SuccessResult();
	}

	@Override
	public Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		Result resultCheck = BusinessRules.run(checkPasswordConfirmationForCorporateCustomerRegistration(registerCorporateCustomerRequest));
		if(resultCheck != null) {
			return resultCheck;
		}
		CreateCorporateCustomerRequest result = modelMapperService.forRequest()
				.map(registerCorporateCustomerRequest, CreateCorporateCustomerRequest.class);
		this.corporateCustomerService.save(result);
		return new SuccessResult("Corporate customer added");
	}

}
