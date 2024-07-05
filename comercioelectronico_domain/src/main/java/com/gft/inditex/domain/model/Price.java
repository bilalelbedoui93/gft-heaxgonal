package com.gft.inditex.domain.model;

import java.time.LocalDateTime;

public class Price implements Comparable<Price>{

	private BrandIdVO brandId;
	
	private DateVO startDate;
	
	private DateVO endDate;
	
	private Integer priceList;
	
	private  ProductIdVO productId;
	
	private Integer priority;
	
	private Double price;
	
	private String currency;
	
	public Price() {}

	public Price(BrandIdVO brandId, DateVO startDate, DateVO endDate, Integer priceList, ProductIdVO productId, Integer priority,
			Double price, String currency) {
		super();
		this.brandId = brandId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priceList = priceList;
		this.productId = productId;
		this.priority = priority;
		this.price = price;
		this.currency = currency;
	}
	
	public BrandIdVO getBrandId() {
		return brandId;
	}

	public void setBrandId(BrandIdVO brandId) {
		this.brandId = brandId;
	}

	public DateVO getStartDate() {
		return startDate;
	}

	public void setStartDate(DateVO startDate) {
		this.startDate = startDate;
	}

	public DateVO getEndDate() {
		return endDate;
	}

	public void setEndDate(DateVO endDate) {
		this.endDate = endDate;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}

	public ProductIdVO getProductId() {
		return productId;
	}

	public void setProductId(ProductIdVO productId) {
		this.productId = productId;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "PriceProduct [brandId=" + brandId.getId() + ", startDate=" + startDate.getDate() + ", endDate=" + endDate.getDate() + ", priceList="
				+ priceList + ", productId=" + productId.getId() + ", priority=" + priority + ", price=" + price + ", currency="
				+ currency + "]";
	}

	@Override
	public int compareTo(Price otherPriceProduct) {
		return Integer.compare(getPriority(), otherPriceProduct.getPriority());
	};
	
	
	
}
