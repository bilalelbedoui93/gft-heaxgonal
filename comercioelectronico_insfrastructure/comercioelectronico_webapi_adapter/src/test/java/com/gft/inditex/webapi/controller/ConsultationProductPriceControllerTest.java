package com.gft.inditex.webapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.inditex.hexagonal.domain.model.BrandIdVO;
import com.gft.inditex.hexagonal.domain.model.DateVO;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.model.ProductIdVO;
import com.gft.inditex.hexagonal.domain.port.in.IVerifyProductPriceToApplyUseCase;
import com.gft.inditex.hexagonal.domain.port.in.query.CriteriaProductPriceQuery;
import com.gft.inditex.webapi.utils.EndPoints;


@WebMvcTest(controllers = ConsultationProductPriceController.class)
@ContextConfiguration(classes = {TestComercioElectronicoApplication.class})
class ConsultationProductPriceControllerTest {

	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	private IVerifyProductPriceToApplyUseCase useCase;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void findPriceWillBeApplied_test() throws Exception{
		//GIVEN
		LocalDateTime startDate = LocalDateTime.of(2020, 01, 01, 00, 00);
		LocalDateTime endDate = LocalDateTime.of(2021, 01, 01, 00, 00);
		Integer productId = 1;
		Integer brandId = 1;
		Integer priceList = 1;
		Integer priority = 0;
		Double price = 12.50;
		String currency = "EUR";

		Price priceToApply = new Price(new BrandIdVO(brandId), new DateVO(startDate), new DateVO(endDate), priceList, new ProductIdVO(productId), priority, price, currency);


		//WHEN
		when(useCase.retrieveProductPrice(any(CriteriaProductPriceQuery.class))).thenReturn(priceToApply);
		
		//THEN 
		 MockHttpServletRequestBuilder requestEndpoint = get(EndPoints.API_VERSION+"/prices")
					.param("dateApplyingPriceString", "2020-06-15-18:00:00")
			        .param("productId", "1")
			        .param("brandId", "1");
		 
		mockMvc.perform(requestEndpoint)
				.andExpect(status().isOk());
		
	}

}
