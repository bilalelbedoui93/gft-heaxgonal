package com.gft.inditex.hexagonal.domain.model;

import java.util.Objects;

import com.gft.inditex.hexagonal.domain.exception.ValueObjectIsNullException;

public class ProductIdVO {

	private Integer id;
	
	public ProductIdVO(Integer id){
		if(!Objects.nonNull(id)) {
			throw new ValueObjectIsNullException("productId");
		}
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
	
}
