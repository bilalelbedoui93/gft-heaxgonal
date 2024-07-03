package com.gft.inditex.persistencesql.adapter;

import java.time.LocalDateTime;
import java.util.List;

import com.gft.inditex.domain.configuration.PersistenceAdapter;
import com.gft.inditex.domain.model.Price;
import com.gft.inditex.domain.port.out.IRetrievePricesPort;
import com.gft.inditex.persistencesql.entity.PriceJpaEntity;
import com.gft.inditex.persistencesql.mapper.PriceMapper;
import com.gft.inditex.persistencesql.repository.IPricesSpringDataRepository;

@PersistenceAdapter
public class PricePersistenceAdapter implements IRetrievePricesPort{

	private IPricesSpringDataRepository pricesRepository;
	
	public PricePersistenceAdapter(IPricesSpringDataRepository pricesRepository) {
		this.pricesRepository=pricesRepository;
	}
	
	@Override
	public List<Price> getListPrices(LocalDateTime dateApplyingPrice, Integer productId, Integer brandId) {
		
		List<PriceJpaEntity> listPrices= pricesRepository.findPricesProductByDate(dateApplyingPrice, productId, brandId);
	
		return PriceMapper.mapListEntitiesToListModel(listPrices);
	}

}
