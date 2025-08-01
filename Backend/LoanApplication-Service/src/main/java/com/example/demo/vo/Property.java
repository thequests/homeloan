package com.example.demo.vo;


public class Property {
	private Integer propertyId;
	private String location;
	private String name;
	private Double estimatedCost;
	private String type;

	// Getters & Setters
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

