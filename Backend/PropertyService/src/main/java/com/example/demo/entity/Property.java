package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PROPERTY_DETAILS")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID")
    private Integer propertyId;

    @Column(name = "LOCATION", length = 255)
    private String location;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "ESTIMATED_COST")
    private Double estimatedCost;

    @Column(name = "TYPE", length = 100)
    private String type;

    // Constructors
    public Property() {
    }

    public Property(Integer propertyId, String location, String name, Double estimatedCost, String type) {
        this.propertyId = propertyId;
        this.location = location;
        this.name = name;
        this.estimatedCost = estimatedCost;
        this.type = type;
    }

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