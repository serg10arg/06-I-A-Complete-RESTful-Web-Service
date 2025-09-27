package com.example.acompleterestfulwebservice.controllers;

import com.example.acompleterestfulwebservice.entities.Product;
import com.example.acompleterestfulwebservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // La decision sobre que hacer si el producto no se encuentra
        // se delega a ResponseEntity.of()
        return ResponseEntity.of(service.findProductById(id));
    }
}
