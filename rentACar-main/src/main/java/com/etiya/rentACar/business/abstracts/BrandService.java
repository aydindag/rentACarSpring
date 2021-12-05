package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.BrandSearchListDto;
import com.etiya.rentACar.business.request.brandRequests.CreateBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.DeleteBrandRequest;
import com.etiya.rentACar.business.request.brandRequests.UpdateBrandRequest;
import com.etiya.rentACar.core.utilities.results.Result;

public interface BrandService {
	List<BrandSearchListDto> getBrands();
	Result save(CreateBrandRequest createBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
}
