package com.etiya.rentACar.core.utilities.adapters.findeksServiceAdapter;

import com.etiya.rentACar.business.externalServices.FakeFindeksService;
import org.springframework.stereotype.Service;

@Service
public class FindeksAdapterManager implements FinancialDataService{

    @Override
    public int getFindeksScore(int userId) {
        FakeFindeksService fakeFindeksService = new FakeFindeksService();
        return fakeFindeksService.getFindeksScore(userId);
    }
}
