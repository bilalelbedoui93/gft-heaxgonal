package com.gft.inditex.persistencesql.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gft.inditex.hexagonal.domain.configuration.PersistenceAdapter;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.port.out.IRetrievePricesPort;
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
	public Optional<Price> getPrice(LocalDateTime dateApplyingPrice, Integer productId, Integer brandId) {
		
		Optional<PriceJpaEntity> priceEntity = pricesRepository.findPriceProductByDate(dateApplyingPrice, productId, brandId);
		
		if(priceEntity.isPresent()) {
			return Optional.of(PriceMapper.mapEntityToDomainModel(priceEntity.get()));
		}
		
		return Optional.empty();
	}

}
