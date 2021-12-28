package com.etiya.rentACar.ws;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.RentalService;
import com.etiya.rentACar.business.dtos.RentalSearchListDto;
import com.etiya.rentACar.business.request.rentalRequests.CreateRentalRequest;
import com.etiya.rentACar.business.request.rentalRequests.DeleteRentalRequest;
import com.etiya.rentACar.business.request.rentalRequests.UpdateRentalRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/rental")
public class RentalController {
	private RentalService rentalService;

	@Autowired
	public RentalController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@GetMapping("list")
	public DataResult<List<RentalSearchListDto>> getAll(){
		return this.rentalService.getAll();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.save(createRentalRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}
	
}
