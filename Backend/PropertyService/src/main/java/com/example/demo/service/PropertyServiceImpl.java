package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Property;
import com.example.demo.repo.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(Integer id) {
        Optional<Property> optional = propertyRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property updateProperty(Property property) {
        return propertyRepository.save(property); // save works for both insert and update
    }

    @Override
    public void deletePropertyById(Integer id) {
        propertyRepository.deleteById(id);
    }
}
