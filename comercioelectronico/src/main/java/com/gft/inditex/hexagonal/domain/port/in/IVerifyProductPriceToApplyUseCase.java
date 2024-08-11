package com.gft.inditex.hexagonal.domain.port.in;

import com.gft.inditex.hexagonal.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.port.in.query.CriteriaProductPriceQuery;

public interface IVerifyProductPriceToApplyUseCase {

	/**
	 * 
	 * @param quey
	 * @return
	 * @throws PriceNotFoundDomainException
	 */
	Price retrieveProductPrice(CriteriaProductPriceQuery quey) throws PriceNotFoundDomainException;
	
}
