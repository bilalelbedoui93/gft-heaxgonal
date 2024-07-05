package com.gft.inditex.domain.exception;

public class IncorrectParametersDomainException extends RuntimeException{

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 4987328927895319356L;
	
	public IncorrectParametersDomainException(String message){
		super(message);
	}
}
