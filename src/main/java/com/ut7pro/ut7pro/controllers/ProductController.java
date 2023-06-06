/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ut7pro.ut7pro.controllers;

import com.ut7pro.ut7pro.exceptions.NotFoundException;
import com.ut7pro.ut7pro.models.Products;
import com.ut7pro.ut7pro.services.ProductService;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productservice.getAllProducts();
    }

    // Apartado añadido para la práctica 7.3

    @GetMapping("/products/search")
    public List<Products> getProducts(int id, double priceGreaterThan, String sortBy) {
        List<Products> filteredProducts = new ArrayList<>();

        for (Products product : productservice.getAllProducts()) {
            if (product.getId() != id) {
                continue;
            }

            if (product.getprecio() < priceGreaterThan) {
                continue;
            }

            filteredProducts.add(product);
        }

        if (sortBy != null) {
            // Ordenar los productos según el parámetro "sortBy"
            Comparator<Products> comparator = Comparator.comparing(Products::getprecio);
            if (sortBy.equalsIgnoreCase("desc")) {
                comparator = comparator.reversed();
            }
            Collections.sort(filteredProducts, comparator);
        }

        return filteredProducts;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getUserById(@PathVariable("id") Long id) {
        try {
            Products products = productservice.getProductById(id);
            return ResponseEntity.ok(products);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Products> createUser(@RequestBody Products products) {
        Products createdProducts = productservice.createProducts(products);
        return ResponseEntity.created(URI.create("/api/v1/products/" + createdProducts.getId())).body(createdProducts);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateUser(@PathVariable("id") Long id, @RequestBody Products products) {
        Products updatedProducts = productservice.updateProducts(products);
        return ResponseEntity.ok(updatedProducts);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Products> updatePatchUser(@PathVariable("id") Long id, @RequestBody Products products) {
        Products patchedProducts = productservice.updateProducts(products);
        return ResponseEntity.ok(patchedProducts);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        productservice.deleteProducts(id);
        return ResponseEntity.noContent().build();
    }
}
