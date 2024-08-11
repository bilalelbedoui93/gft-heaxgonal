package com.gft.inditex.persistencesql.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="PRICES")
public class PriceJpaEntity {

	@Column(name="BRAND_ID")
	private Integer brandId;
	
	@Column(name="START_DATE")
	private LocalDateTime startDate;
	
	@Column(name="END_DATE")
	private LocalDateTime endDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRICES_SEQ")
	@SequenceGenerator(name = "PRICES_SEQ", sequenceName = "PRICES_SEQ", allocationSize = 1)
	@Column(name="PRICE_LIST")
	private Integer priceList;
	
	@Column(name="PRODUCT_ID")
	private  Integer productId;
	
	@Column(name="PRIORITY")
	private Integer Priority;
	
	@Column(name="PRICE")	
	private Double price;
	
	@Column(name="CURR")
	private String currency;
	
	public PriceJpaEntity() {};
	
	public PriceJpaEntity(Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Integer productId,
			Integer priority, Double price, String currency) {
		super();
		this.brandId = brandId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priceList = priceList;
		this.productId = productId;
		Priority = priority;
		this.price = price;
		this.currency = currency;
	}

	public Integer getBrandId() {
		return brandId;
	}
	
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPriority() {
		return Priority;
	}

	public void setPriority(Integer priority) {
		Priority = priority;
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
	
}
