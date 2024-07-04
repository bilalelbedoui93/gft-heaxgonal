package com.gft.inditex.webapi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gft.inditex.domain.configuration.WebAdapter;
import com.gft.inditex.domain.exception.IncorrectParametersDomainException;
import com.gft.inditex.domain.exception.PriceNotFoundDomainException;
import com.gft.inditex.domain.model.BrandIdVO;
import com.gft.inditex.domain.model.DateVO;
import com.gft.inditex.domain.model.Price;
import com.gft.inditex.domain.model.ProductIdVO;
import com.gft.inditex.domain.port.in.IVerifyProductPriceToApplyUseCase;
import com.gft.inditex.domain.port.in.query.CriteriaProductPriceQuery;
import com.gft.inditex.webapi.configuration.EndPoints;
import com.gft.inditex.webapi.generated.api.PricesApi;
import com.gft.inditex.webapi.generated.model.PriceToApplyResponse;

@WebAdapter
@RestController
@RequestMapping(EndPoints.API_VERSION)
public class ConsultationProductPriceController implements PricesApi{

	private IVerifyProductPriceToApplyUseCase useCase;
	
	public ConsultationProductPriceController(IVerifyProductPriceToApplyUseCase useCase) {
		this.useCase = useCase;
	}

	@Override
	public ResponseEntity<PriceToApplyResponse> _getProductPrice(
			@NotNull @Valid String dateApplyingPriceString, @NotNull @Valid Integer productId,
			@NotNull @Valid Integer brandId) {
		
		try {
			
			CriteriaProductPriceQuery criteria = new CriteriaProductPriceQuery(
					new DateVO(dateApplyingPriceString, DateVO.DATE_STRING_FORMAT),
					new ProductIdVO(productId),
					new BrandIdVO(brandId));
			
			Price price = useCase.retrieveProductPrice(criteria);
			
			PriceToApplyResponse response = new PriceToApplyResponse();
			response.setProductId(price.getProductId().getId());
			response.setBrandId(price.getBrandId().getId());
			response.setStartDate(price.getStartDate().getDate());
			response.setEndDate(price.getEndDate().getDate());
			response.setPrice(price.getPrice());
			
			return new ResponseEntity<PriceToApplyResponse>(response, HttpStatus.OK);
		
		
		}catch(IncorrectParametersDomainException e) {
	       throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}catch (PriceNotFoundDomainException e) {
	       throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
		
	}

}
