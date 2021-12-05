package com.etiya.rentACar.ws;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.LoginService;
import com.etiya.rentACar.business.request.loginAndRegister.LoginRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterCorporateCustomerRequest;
import com.etiya.rentACar.business.request.loginAndRegister.RegisterIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/login")
public class LoginController {
	
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	Result login(@Valid @RequestBody LoginRequest loginRequest) {
		return this.loginService.login(loginRequest);
	}
	
	@PostMapping("/registerIndividualCustomer")
	Result individualCustomerRegister(@RequestBody @Valid RegisterIndividualCustomerRequest registerIndividualCustomerRequest ){
		return this.loginService.individualCustomerRegister(registerIndividualCustomerRequest);
	}
	@PostMapping("/registerCorporateCustomer")
	Result corporateCustomerRegister(@RequestBody @Valid RegisterCorporateCustomerRequest registerCorporateCustomerRequest ){
		return this.loginService.corporateCustomerRegister(registerCorporateCustomerRequest);
	}

}