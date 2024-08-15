package com.gft.inditex.hexagonal.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.gft.inditex.hexagonal.domain.model.Price;

public interface IRetrievePricesPort {

	/**
	 * 
	 * @param dateApplyingPrice
	 * @param productId
	 * @param brandId
	 * @return
	 */
	List<Price> getListPrices(LocalDateTime dateApplyingPrice, Integer productId, Integer brandId);

}
