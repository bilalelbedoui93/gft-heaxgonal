package com.gft.inditex.domain.port.in;

import com.gft.inditex.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.domain.model.Price;
import com.gft.inditex.domain.port.in.query.CriteriaProductPriceQuery;

public interface IVerifyProductPriceToApplyUseCase {

	/**
	 * 
	 * @param quey
	 * @return
	 * @throws PriceNotFoundDomainException
	 */
	Price retrieveProductPrice(CriteriaProductPriceQuery quey) throws PriceNotFoundDomainException;
	
}
