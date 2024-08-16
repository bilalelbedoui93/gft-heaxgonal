package com.gft.inditex.persistencesql.adapter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

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
		
		PriceJpaEntity price1 = new PriceJpaEntity();
		price1.setBrandId(brandId);
		price1.setCurrency("EUR");
		price1.setStartDate(LocalDateTime.of(2021, 01, 01, 00, 00));
		price1.setEndDate(LocalDateTime.of(2021, 12, 31, 23, 59));
		price1.setProductId(productId);
		price1.setPrice(18.45);
		price1.setPriceList(1);
		price1.setPriority(0);
		
		PriceJpaEntity price2 = new PriceJpaEntity();
		price2.setBrandId(brandId);
		price2.setCurrency("EUR");
		price2.setStartDate(LocalDateTime.of(2021, 05, 01, 00, 00));
		price2.setEndDate(LocalDateTime.of(2021, 05, 31, 23, 59));
		price2.setProductId(productId);
		price2.setPrice(9.30);
		price2.setPriceList(2);
		price2.setPriority(1);
		
		List<PriceJpaEntity> listPrices = List.of(price1, price2);
		
		//WHEN
		when(repository.findPricesProductByDate(dateApplyingPrice, productId, brandId)).thenReturn(listPrices);
		
		List<Price> listPricesDomainResult = persistenceAdapter.getListPrices(dateApplyingPrice, productId,brandId);
		
		//THEN
		assertTrue(listPricesDomainResult.size() == 2);
		assertTrue(listPricesDomainResult.stream().map(Price::getPriority)
									.anyMatch(anyPriority -> List.of(price1.getPriority(), price2.getPriority()).contains(anyPriority)));
		assertTrue(listPricesDomainResult.stream().map(Price::getPrice)
				.anyMatch(anyPrice -> List.of(price1.getPrice(), price2.getPrice()).contains(anyPrice)));

	}

}
