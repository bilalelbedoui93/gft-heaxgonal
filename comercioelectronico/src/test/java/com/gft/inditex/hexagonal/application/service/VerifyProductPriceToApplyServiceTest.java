package com.gft.inditex.hexagonal.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gft.inditex.hexagonal.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.hexagonal.domain.model.BrandIdVO;
import com.gft.inditex.hexagonal.domain.model.DateVO;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.model.ProductIdVO;
import com.gft.inditex.hexagonal.domain.port.in.query.CriteriaProductPriceQuery;
import com.gft.inditex.hexagonal.domain.port.out.IRetrievePricesPort;

@ExtendWith(MockitoExtension.class)
class VerifyProductPriceToApplyServiceTest {

	@Mock
	private IRetrievePricesPort retrievePricesPortMock;
	
	@InjectMocks
	private VerifyProductPriceToApplyService useCase;
			
	
	@Test
	public void uniquePrice_returnedCorrectly_test() {
		// Given
		LocalDateTime startDate = LocalDateTime.of(2020, 01, 01, 00, 00);
		LocalDateTime endDate = LocalDateTime.of(2021, 01, 01, 00, 00);
		Integer priceList = 1;
		Integer priority = 0;
		Double price = 12.50;
		String currency = "EUR";
		
		LocalDateTime dateApplying = LocalDateTime.of(2020, 06, 15, 18, 00);
		Integer productId = 1;
		Integer brandId = 1;
		
		CriteriaProductPriceQuery criteria = new CriteriaProductPriceQuery(new DateVO(dateApplying), new ProductIdVO(productId), new BrandIdVO(brandId));

		List<Price> listPrices = new ArrayList<Price>();
		listPrices.add(new Price(new BrandIdVO(brandId), new DateVO(startDate), new DateVO(endDate), priceList, new ProductIdVO(productId), priority, price, currency));
		
		// When
		when(retrievePricesPortMock.getListPrices(criteria.dateApplyingPrice().getDate(),
															criteria.productId().getId(),
															criteria.brandId().getId()))
				.thenReturn(listPrices);
		
		Price actualPrice = useCase.retrieveProductPrice(criteria);
		
		// Then
		assertDoesNotThrow(() -> useCase.retrieveProductPrice(criteria));
		assertNotNull(actualPrice);
		assertEquals(brandId, actualPrice.getBrandId().getId());
		assertEquals(startDate, actualPrice.getStartDate().getDate());
		assertEquals(endDate, actualPrice.getEndDate().getDate());
		assertEquals(priceList, actualPrice.getPriceList());
		assertEquals(productId, actualPrice.getProductId().getId());
		assertEquals(priority, actualPrice.getPriority());
		assertEquals(price, actualPrice.getPrice());
		assertEquals(currency, actualPrice.getCurrency());

	}
	
	@Test
	public void twoPrices_highestPrioriryIsReturned_test() {
		// Given
		LocalDateTime startDate = LocalDateTime.of(2020, 01, 01, 00, 00);
		LocalDateTime endDate = LocalDateTime.of(2021, 01, 01, 00, 00);
		Integer priceList = 1;
		Integer priority = 0;
		Double price = 12.50;
		String currency = "EUR";
		
		LocalDateTime startDate2 = LocalDateTime.of(2020, 06, 18, 00, 00);
		LocalDateTime endDate2 = LocalDateTime.of(2021, 07, 15, 00, 00);
		Integer priceList2 = 2;
		Integer priority2 = 1;
		Double price2 = 19.50;
		
		LocalDateTime dateApplying = LocalDateTime.of(2020, 07, 03, 18, 00);
		Integer productId = 1;
		Integer brandId = 1;
		
		CriteriaProductPriceQuery criteria = new CriteriaProductPriceQuery(new DateVO(dateApplying), new ProductIdVO(productId), new BrandIdVO(brandId));

		List<Price> listPrices = new ArrayList<Price>();
		listPrices.add(new Price(new BrandIdVO(brandId), new DateVO(startDate), new DateVO(endDate), priceList, new ProductIdVO(productId), priority, price, currency));
		listPrices.add(new Price(new BrandIdVO(brandId), new DateVO(startDate2), new DateVO(endDate2), priceList2, new ProductIdVO(productId), priority2, price2, currency));

		// When
		when(retrievePricesPortMock.getListPrices(criteria.dateApplyingPrice().getDate(),
															criteria.productId().getId(),
															criteria.brandId().getId()))
				.thenReturn(listPrices);
		
		Price actualPrice = useCase.retrieveProductPrice(criteria);
		
		// Then
		assertDoesNotThrow(() -> useCase.retrieveProductPrice(criteria));
		assertNotNull(actualPrice);
		assertEquals(brandId, actualPrice.getBrandId().getId());
		assertEquals(startDate2, actualPrice.getStartDate().getDate());
		assertEquals(endDate2, actualPrice.getEndDate().getDate());
		assertEquals(priceList2, actualPrice.getPriceList());
		assertEquals(productId, actualPrice.getProductId().getId());
		assertEquals(priority2, actualPrice.getPriority());
		assertEquals(price2, actualPrice.getPrice());
		assertEquals(currency, actualPrice.getCurrency());
	}
	
	@Test
	public void throws_PriceNotFoundDomainException_whenNoPriceIsFound_test() {
		// Given	
		LocalDateTime dateApplying = LocalDateTime.of(2021, 01, 01, 12, 00);
		Integer productId = 1;
		Integer brandId = 1;
		
		CriteriaProductPriceQuery criteria = new CriteriaProductPriceQuery(new DateVO(dateApplying), new ProductIdVO(productId), new BrandIdVO(brandId));

		List<Price> emptyListPrices = new ArrayList<Price>();
		
		// When
		when(retrievePricesPortMock.getListPrices(criteria.dateApplyingPrice().getDate(),
															criteria.productId().getId(),
															criteria.brandId().getId()))
				.thenReturn(emptyListPrices);
				
		// Then
		assertThrows(PriceNotFoundDomainException.class, (() -> useCase.retrieveProductPrice(criteria)));
	}
	
	public void throw_NotValidLocalDateTime_whenProvidedDateStringCannotBeParsed_test() {
		
	}
}
