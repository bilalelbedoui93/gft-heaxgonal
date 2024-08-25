package com.gft.inditex.persistencesql.adapter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.persistencesql.entity.PriceJpaEntity;
import com.gft.inditex.persistencesql.repository.IPricesSpringDataRepository;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTest {

	@Mock
	private IPricesSpringDataRepository repository;
	
	@InjectMocks
	private PricePersistenceAdapter persistenceAdapter;
	
	@Test
	void getListOfTwoPricesBelongingToSpecificProductBrand() {

		//GIVEN
		Integer brandId = 1;
		Integer productId = 1;
		LocalDateTime dateApplyingPrice = LocalDateTime.of(2021, 05, 15, 17, 30);
		
		PriceJpaEntity price = new PriceJpaEntity();
		price.setBrandId(brandId);
		price.setCurrency("EUR");
		price.setStartDate(LocalDateTime.of(2021, 01, 01, 00, 00));
		price.setEndDate(LocalDateTime.of(2021, 12, 31, 23, 59));
		price.setProductId(productId);
		price.setPrice(18.45);
		price.setPriceList(1);
		price.setPriority(0);
		
		
		//WHEN
		when(repository.findPriceProductByDate(dateApplyingPrice, productId, brandId)).thenReturn(Optional.of(price));
		
		Price priceResult = persistenceAdapter.getPrice(dateApplyingPrice, productId,brandId).get();
		
		//THEN
		assertTrue(Objects.nonNull(priceResult));
		MatcherAssert.assertThat(priceResult.getPrice(), Matchers.equalTo(price.getPrice()));
		MatcherAssert.assertThat(priceResult.getPriority(), Matchers.equalTo(price.getPriority()));
	}

}
