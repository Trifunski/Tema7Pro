/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.ut7pro.ut7pro.services;

import com.ut7pro.ut7pro.exceptions.NotFoundException;
import com.ut7pro.ut7pro.models.Products;
import com.ut7pro.ut7pro.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: "+ id));
    }

    public Products createProducts(Products newProducts) {
        return productRepository.save(newProducts);
    }

    public Products updateProducts(Products updatedProducts) {
        return productRepository.save(updatedProducts);
    }

    public void deleteProducts(Long id) {
        productRepository.deleteById(id);
    }
}
