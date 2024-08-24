package com.gft.inditex.hexagonal.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.gft.inditex.hexagonal.domain.model.Price;

public interface IRetrievePricesPort {

	/**
	 * Get price from the SGBD 
	 * 
	 * @param dateApplyingPrice
	 * @param productId
	 * @param brandId
	 * @return the price
	 */
	List<Price> getListPrices(LocalDateTime dateApplyingPrice, Integer productId, Integer brandId);

}
