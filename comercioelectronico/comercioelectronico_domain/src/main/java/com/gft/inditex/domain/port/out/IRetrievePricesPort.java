package com.gft.inditex.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com.gft.inditex.domain.model.Price;

public interface IRetrievePricesPort {

	List<Price> getListPrices(LocalDateTime dateApplyingPrice, Integer productId, Integer brandId);

}
