package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Property;

public interface PropertyService {
    Property saveProperty(Property property);
    Property getPropertyById(Integer id);
    List<Property> getAllProperties();
    Property updateProperty(Property property);
    void deletePropertyById(Integer id);
}
