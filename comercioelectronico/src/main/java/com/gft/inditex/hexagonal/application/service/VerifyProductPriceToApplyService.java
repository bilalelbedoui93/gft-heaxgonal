package com.gft.inditex.hexagonal.application.service;

import java.util.Optional;

import com.gft.inditex.hexagonal.domain.configuration.UseCase;
import com.gft.inditex.hexagonal.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.port.in.IVerifyProductPriceToApplyUseCase;
import com.gft.inditex.hexagonal.domain.port.in.query.CriteriaProductPriceQuery;
import com.gft.inditex.hexagonal.domain.port.out.IRetrievePricesPort;

@UseCase
public class VerifyProductPriceToApplyService implements IVerifyProductPriceToApplyUseCase{

	private IRetrievePricesPort retrievePricesPort;
	
	public VerifyProductPriceToApplyService(IRetrievePricesPort retrievePricesPort) {
		this.retrievePricesPort=retrievePricesPort;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Price retrieveProductPrice(CriteriaProductPriceQuery query) throws PriceNotFoundDomainException {	
		
		Optional<Price> price = retrievePricesPort.getPrice(query.dateApplyingPrice().getDate(),
				query.productId().getId(),
				query.brandId().getId());
		
		if(price.isEmpty()) {
			throw new PriceNotFoundDomainException();
		}
		
		return price.get();
	}

}
