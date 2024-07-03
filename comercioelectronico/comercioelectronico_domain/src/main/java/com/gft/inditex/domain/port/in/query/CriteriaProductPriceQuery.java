package com.gft.inditex.domain.port.in.query;

import com.gft.inditex.domain.model.BrandIdVO;
import com.gft.inditex.domain.model.DateVO;
import com.gft.inditex.domain.model.ProductIdVO;

public record CriteriaProductPriceQuery(DateVO dateApplyingPrice, ProductIdVO productId, BrandIdVO brandId) {

}
