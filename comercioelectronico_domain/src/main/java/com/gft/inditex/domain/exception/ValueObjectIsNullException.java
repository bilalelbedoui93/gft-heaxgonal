package com.gft.inditex.domain.exception;

public class ValueObjectIsNullException extends IncorrectParametersDomainException {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 6104175943795641418L;

	private static final String ERROR_MESSAGE = "object.is.null";
	
	public ValueObjectIsNullException(String nullObjectName) {
		super(ERROR_MESSAGE.replace("object", nullObjectName.toLowerCase()));
	}
}
