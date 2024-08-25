package com.gft.inditex.hexagonal.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

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

		Price priceProduct = new Price(new BrandIdVO(brandId), new DateVO(startDate), new DateVO(endDate), priceList, new ProductIdVO(productId), priority, price, currency);
		
		// When
		when(retrievePricesPortMock.getPrice(criteria.dateApplyingPrice().getDate(),
															criteria.productId().getId(),
															criteria.brandId().getId()))
				.thenReturn(Optional.of(priceProduct));
		
		Price resultPrice = useCase.retrieveProductPrice(criteria);
		
		// Then
		assertDoesNotThrow(() -> useCase.retrieveProductPrice(criteria));
		assertNotNull(resultPrice);
		assertEquals(brandId, resultPrice.getBrandId().getId());
		assertEquals(startDate, resultPrice.getStartDate().getDate());
		assertEquals(endDate, resultPrice.getEndDate().getDate());
		assertEquals(priceList, resultPrice.getPriceList());
		assertEquals(productId, resultPrice.getProductId().getId());
		assertEquals(priority, resultPrice.getPriority());
		assertEquals(price, resultPrice.getPrice());
		assertEquals(currency, resultPrice.getCurrency());

	}
	
	@Test
	public void throws_PriceNotFoundDomainException_whenNoPriceIsFound_test() {
		// Given	
		LocalDateTime dateApplying = LocalDateTime.of(2021, 01, 01, 12, 00);
		Integer productId = 1;
		Integer brandId = 1;
		
		CriteriaProductPriceQuery criteria = new CriteriaProductPriceQuery(new DateVO(dateApplying), new ProductIdVO(productId), new BrandIdVO(brandId));

		
		// When
		when(retrievePricesPortMock.getPrice(criteria.dateApplyingPrice().getDate(),
															criteria.productId().getId(),
															criteria.brandId().getId()))
				.thenReturn(Optional.empty());
				
		// Then
		assertThrows(PriceNotFoundDomainException.class, (() -> useCase.retrieveProductPrice(criteria)));
	}
}
