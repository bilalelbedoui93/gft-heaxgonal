package com.gft.inditex.hexagonal.domain.exception;

public class NotValidLocalDateTimeException extends IncorrectParametersDomainException{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 2883700721450881452L;

	private static final String ERROR_MESSAGE = "date.is.not.valid";
	
	public NotValidLocalDateTimeException() {
		super(ERROR_MESSAGE);
	}
}
