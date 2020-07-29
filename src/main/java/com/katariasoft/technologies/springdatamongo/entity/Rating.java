package com.katariasoft.technologies.springdatamongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Rating {

	@Id
	private String id;
	private String merchantId;
	private String merchantName;
	private boolean isActive;
	private double aggregatedRating;

	public Rating(String merchantId, String merchantName, boolean isActive, double aggregatedRating) {
		super();
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.isActive = isActive;
		this.aggregatedRating = aggregatedRating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getAggregatedRating() {
		return aggregatedRating;
	}

	public void setAggregatedRating(double aggregatedRating) {
		this.aggregatedRating = aggregatedRating;
	}

}
