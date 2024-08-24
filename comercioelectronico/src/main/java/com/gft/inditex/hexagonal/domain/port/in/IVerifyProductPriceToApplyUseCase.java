package com.gft.inditex.hexagonal.domain.port.in;

import com.gft.inditex.hexagonal.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.port.in.query.CriteriaProductPriceQuery;

public interface IVerifyProductPriceToApplyUseCase {

	/**
	 * The use case that will retrieve the price of a specified produced in a concrete timestamp
	 * 
	 * @param query contains the arguments to find the price
	 * @return price
	 * @throws PriceNotFoundDomainException
	 */
	Price retrieveProductPrice(CriteriaProductPriceQuery query) throws PriceNotFoundDomainException;
	
}
