package com.gft.inditex.hexagonal.domain.port.in.query;

import com.gft.inditex.hexagonal.domain.model.BrandIdVO;
import com.gft.inditex.hexagonal.domain.model.DateVO;
import com.gft.inditex.hexagonal.domain.model.ProductIdVO;

public record CriteriaProductPriceQuery(DateVO dateApplyingPrice, ProductIdVO productId, BrandIdVO brandId) {

}
