package com.etiya.rentACar.core.utilities.adapters;

import org.springframework.stereotype.Service;

import com.etiya.rentACar.core.utilities.externalServices.FindeksDataProviderService;

@Service
public class FindeksDataProviderAdapter implements FindeksDataProviderService {

	@Override
	public int findeksPointGenerator() {
		return 900;
	}

}
