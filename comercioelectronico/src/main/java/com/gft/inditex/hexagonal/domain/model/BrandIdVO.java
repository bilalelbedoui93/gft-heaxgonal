package com.gft.inditex.hexagonal.domain.model;

import java.util.Objects;

import com.gft.inditex.hexagonal.domain.configuration.IValueObject;
import com.gft.inditex.hexagonal.domain.exception.ValueObjectIsNullException;

public class BrandIdVO implements IValueObject{

	private Integer id;
	
	public BrandIdVO(Integer id) {
		if(!Objects.nonNull(id)) {
			throw new ValueObjectIsNullException("brandid");
		}
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
}
