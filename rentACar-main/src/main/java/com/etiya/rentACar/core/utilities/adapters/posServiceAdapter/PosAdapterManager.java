package com.etiya.rentACar.core.utilities.adapters.posServiceAdapter;

import com.etiya.rentACar.business.externalServices.FakePosService;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.entities.CreditCard;
import com.etiya.rentACar.entities.complexTypes.CreditCardDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosAdapterManager implements PaymentApprovementService{
    private ModelMapperService modelMapperService;

    @Autowired
    public PosAdapterManager(ModelMapperService modelMapperService) {
        this.modelMapperService = modelMapperService;
    }

    @Override
    public boolean checkPaymentSuccess(CreditCard creditCard) {
        FakePosService fakePosService = new FakePosService();
        CreditCardDetail creditCardDetail = modelMapperService.forDto().map(creditCard, CreditCardDetail.class);
        return fakePosService.isPaymentSuccessful(creditCardDetail);
    }
}
