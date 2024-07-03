package com.gft.inditex.domain.exception;

public class PriceNotFoundDomainException extends RuntimeException{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -225168196166897571L;
	
	private static final String ERROR_MESSAGE = "price.not.found";

	public PriceNotFoundDomainException() {
		super(ERROR_MESSAGE);
	}
			
}
