package com.gft.inditex.webapi.controller.exceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gft.inditex.hexagonal.domain.exception.IncorrectParametersDomainException;
import com.gft.inditex.hexagonal.domain.exception.PriceNotFoundDomainException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {PriceNotFoundDomainException.class})
	public ResponseEntity<ApiException> handlePriceNotFoundDomainException(PriceNotFoundDomainException exception){
		
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException responseException = new ApiException(
				exception.getMessage(),
				notFound,
				ZonedDateTime.now(ZoneId.of("Z")));
		
		return new ResponseEntity<>(responseException, notFound);
	}
	
	@ExceptionHandler(value = {IncorrectParametersDomainException.class})
	public ResponseEntity<ApiException> handleIncorrectParametersDomainException(IncorrectParametersDomainException exception){
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException responseException = new ApiException(
				exception.getMessage(),
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z")));
		
		return new ResponseEntity<>(responseException, badRequest);


	}

}
