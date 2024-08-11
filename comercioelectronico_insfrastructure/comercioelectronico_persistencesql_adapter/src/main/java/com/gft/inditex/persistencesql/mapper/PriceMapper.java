package com.gft.inditex.persistencesql.mapper;

import java.util.List;

import com.gft.inditex.hexagonal.domain.model.BrandIdVO;
import com.gft.inditex.hexagonal.domain.model.DateVO;
import com.gft.inditex.hexagonal.domain.model.Price;
import com.gft.inditex.hexagonal.domain.model.ProductIdVO;
import com.gft.inditex.persistencesql.entity.PriceJpaEntity;

public class PriceMapper {

	public static Price mapEntityToDomainModel(PriceJpaEntity entity) {
		
		return new Price(
				new BrandIdVO(entity.getBrandId()),
				new DateVO(entity.getStartDate()),
				new DateVO(entity.getEndDate()),
				entity.getPriceList(), 
				new ProductIdVO(entity.getProductId()),
				entity.getPriority(),
				entity.getPrice(),
				entity.getCurrency());
	}
	
	public static List<Price> mapListEntitiesToListModel(List<PriceJpaEntity> listEntities){
		
		return listEntities.stream().map(entity -> mapEntityToDomainModel(entity)).toList();
		
	}
}
