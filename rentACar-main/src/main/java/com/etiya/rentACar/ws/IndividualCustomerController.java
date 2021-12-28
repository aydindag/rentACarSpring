package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.IndividualCustomerService;
import com.etiya.rentACar.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.rentACar.business.request.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.rentACar.business.request.individualCustomerRequests.DeleteIndividualCustomerRequest;
import com.etiya.rentACar.business.request.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/individualCustomer")
public class IndividualCustomerController {
	
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService; 
	}
	
	@GetMapping("list")
	public DataResult<List<IndividualCustomerSearchListDto>> getAll(){
		return this.individualCustomerService.getAll();
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

}
