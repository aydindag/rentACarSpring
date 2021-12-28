package com.etiya.rentACar;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.etiya.rentACar.business.constants.messages.ExceptionMessages;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etiya.rentACar.core.utilities.results.ErrorDataResult;
import com.etiya.rentACar.core.utilities.results.ErrorResult;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityNotFoundException;

@SpringBootApplication
@EnableSwagger2
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.etiya.rentACar"))
				.build();
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> validationErrors = new HashMap<String, String>();

		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> error = new ErrorDataResult<Object>(validationErrors, "Validation Errors");
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleNoSuchElementException(NoSuchElementException exception) {

		ErrorResult error = new ErrorResult("No such element exception. " + exception.getMessage());
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

		ErrorResult error = new ErrorResult(ExceptionMessages.wrongInput);
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

		ErrorResult error = new ErrorResult(ExceptionMessages.wrongInput);
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleEntityNotFoundException(EntityNotFoundException exception) {

		ErrorResult error = new ErrorResult(ExceptionMessages.wrongInput);
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleNumberFormatException(NumberFormatException exception) {

		ErrorResult error = new ErrorResult(ExceptionMessages.wrongInput);
		return error;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {

		ErrorResult error = new ErrorResult(ExceptionMessages.argumentTypeMismatch);
		return error;
	}
}
