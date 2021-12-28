package com.etiya.rentACar.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.etiya.rentACar.business.abstracts.UserService;
import com.etiya.rentACar.business.constants.messages.CreditCardMessages;
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
import org.apache.commons.lang3.StringUtils;



@Service
public class CreditCardManager implements CreditCardService {

	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	
	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService, UserService userService) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
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
		Result result = BusinessRules.run(checkCreditCardFormat(createCreditCardRequest.getCardNumber()), userService.existsById(createCreditCardRequest.getUserId()),
				checkIfCvcIsNumeric(createCreditCardRequest.getCvc()));
		if(result != null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(CreditCardMessages.add);
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		Result result = BusinessRules.run(checkCreditCardFormat(updateCreditCardRequest.getCardNumber()), userService.existsById(updateCreditCardRequest.getUserId()),
				checkIfCardIdExists(updateCreditCardRequest.getCardId()));
		if(result != null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(CreditCardMessages.update);
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		Result result = BusinessRules.run(checkIfCardIdExists(deleteCreditCardRequest.getCardId()));
		if(result != null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(deleteCreditCardRequest, CreditCard.class);
		this.creditCardDao.delete(creditCard);
		return new SuccessResult(CreditCardMessages.delete);
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
			return new ErrorResult(CreditCardMessages.invalidCardNumberFormat);
		}
		return new SuccessResult();
	}

	private Result checkIfCvcIsNumeric(String cvc){
		if(StringUtils.isNumeric(cvc)){
			return new SuccessResult();
		}
		else {
			return new ErrorResult(CreditCardMessages.invalidCvcFormat);
		}
	}

	private Result checkIfCardIdExists(int cardID){
		if(creditCardDao.existsById(cardID)){
			return new SuccessResult();
		}
		return new ErrorResult(CreditCardMessages.cardIdDoesNotExist);
	}

}
