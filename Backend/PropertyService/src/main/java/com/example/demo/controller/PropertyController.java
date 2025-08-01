package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Integer id) {
        return propertyService.getPropertyById(id);
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @PutMapping
    public Property updateProperty(@RequestBody Property property) {
        return propertyService.updateProperty(property);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Integer id) {
        propertyService.deletePropertyById(id);
    }
}
