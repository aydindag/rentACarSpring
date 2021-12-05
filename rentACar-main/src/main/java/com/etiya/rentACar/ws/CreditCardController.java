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

import com.etiya.rentACar.business.abstracts.CreditCardService;
import com.etiya.rentACar.business.dtos.CreditCardDto;
import com.etiya.rentACar.business.request.creditCardRequests.CreateCreditCardRequest;
import com.etiya.rentACar.business.request.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.rentACar.business.request.creditCardRequests.UpdateCreditCardRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/creditCard")
public class CreditCardController {

	private CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}
	
	@GetMapping("list")
	private DataResult<List<CreditCardDto>> getAll(){
		return this.creditCardService.getAll();
	}
	@PostMapping("save")
	private Result save(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.create(createCreditCardRequest);
	}
	@DeleteMapping("delete")
	private Result delete(@RequestBody @Valid DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
	@PutMapping("update")
	private Result update(@RequestBody @Valid UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}
	
}
