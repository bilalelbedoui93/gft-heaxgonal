package com.gft.inditex.domain.service;

import java.util.Comparator;
import java.util.List;

import com.gft.inditex.domain.configuration.UseCase;
import com.gft.inditex.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.domain.model.Price;
import com.gft.inditex.domain.port.in.IVerifyProductPriceToApplyUseCase;
import com.gft.inditex.domain.port.in.query.CriteriaProductPriceQuery;
import com.gft.inditex.domain.port.out.IRetrievePricesPort;

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

		List<Price> listPrices = retrievePricesPort.getListPrices(query.dateApplyingPrice().getDate(),
																query.productId().getId(),
																query.brandId().getId());
		
		if(listPrices.isEmpty()) {
			throw new PriceNotFoundDomainException();
		}
		
		return listPrices.stream().sorted(Comparator.reverseOrder()).findFirst().get();		
	}

}
