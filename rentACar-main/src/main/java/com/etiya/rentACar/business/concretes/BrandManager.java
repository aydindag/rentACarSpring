package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.BrandService;
import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.request.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.BrandDao;
import com.etiya.rentACar.entities.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<BrandSearchListDto> getBrands() {
		
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream().map(brand -> modelMapperService.forDto()
				.map(brand, BrandSearchListDto.class)).collect(Collectors.toList());
		
		return response;
	}

	@Override
	public Result save(CreateBrandRequest createBrandRequest) {
		Result result = BusinessRules.run(checkExistingBrand(createBrandRequest.getBrandName()));
		
		if(result != null) {
			return result;
		}
		
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Brand added.");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);	
		return new SuccessResult("Brand deleted.");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
	Result result = BusinessRules.run(checkExistingBrand(updateBrandRequest.getBrandName()),
			checkExistingBrand(updateBrandRequest.getBrandId()));
		
		if(result != null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Brand updated.");
	}
	
	private Result checkExistingBrand(String brandName1) {
		String lowerCaseBrandName = brandName1.toLowerCase();
		for (Brand brand : brandDao.findAll()) {
			String lowerCaseExistingBrandName = brand.getBrandName().toLowerCase();
			if(lowerCaseBrandName.equals(lowerCaseExistingBrandName)) {
				return new ErrorResult("Brand name cannot duplicate!");
			}
		}
		return new SuccessResult();
	}

	private Result checkExistingBrand(int brandId){
		boolean isExisting = brandDao.existsById(brandId);
		if (!isExisting){
			return new ErrorResult("ID not found!");
		}
		return new SuccessResult();
	}

}
