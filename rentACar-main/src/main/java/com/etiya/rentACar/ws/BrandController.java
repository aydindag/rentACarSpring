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

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.request.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/brand")
public class BrandController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("list")
	public List<BrandSearchListDto> getBrands(){
		return this.brandService.getBrands();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		return this.brandService.save(createBrandRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
}
