package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACar.business.abstracts.CreditCardService;
import com.etiya.rentACar.business.dtos.CreditCardDto;
import com.etiya.rentACar.business.request.creditCardRequests.CreateCreditCardRequest;
import com.etiya.rentACar.business.request.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.rentACar.business.request.creditCardRequests.UpdateCreditCardRequest;
import com.etiya.rentACar.core.utilities.business.BusinessRules;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CreditCardDao;
import com.etiya.rentACar.entities.CreditCard;


@Service
public class CreditCardManager implements CreditCardService {

	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CreditCardDto>> getAll() {
		List<CreditCard> result = creditCardDao.findAll();
		List<CreditCardDto> response = result.stream().map(creditCard -> modelMapperService.forDto().
				map(creditCard, CreditCardDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CreditCardDto>>(response);
	}

	@Override
	public Result create(CreateCreditCardRequest createCreditCardRequest) {
		Result result = BusinessRules.run(checkCreditCardFormat(createCreditCardRequest.getCardNumber()));
		if(result != null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult("Card saved.");
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		Result result = BusinessRules.run(checkCreditCardFormat(updateCreditCardRequest.getCardNumber()));
		if(result != null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult("Card updated.");
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		CreditCard creditCard = modelMapperService.forRequest().map(deleteCreditCardRequest, CreditCard.class);
		this.creditCardDao.delete(creditCard);
		return new SuccessResult("Card deleted.");
	}

	@Override
	public CreditCard getById(int cardId) {
		return creditCardDao.getById(cardId);
	}

	private Result checkCreditCardFormat(String cardNumber) {
		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
		        "(?<mastercard>5[1-5][0-9]{14}))$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cardNumber);
		if(!matcher.matches()) {
			return new ErrorResult("Invalid credit card format");
		}
		return new SuccessResult();
	}

}
