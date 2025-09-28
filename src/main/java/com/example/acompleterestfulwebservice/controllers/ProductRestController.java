package com.example.acompleterestfulwebservice.controllers;

import com.example.acompleterestfulwebservice.entities.Product;
import com.example.acompleterestfulwebservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(params = "min")
    public List<Product> getProductsByMinPrice(@RequestParam(defaultValue = "0.0") double min) {
        if(min < 0 ) throw new ProductMinimumPriceException(min);
        return service.findAllProductsByMinPrice(min);
    }

    @GetMapping("count")
    public long getProductCount() {
        return service.countProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product p  = service.saveProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return service.findProductById(id)
                .map(p -> {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    return ResponseEntity.ok(service.saveProduct(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.findProductById(id)
                .map(p -> {
                    service.deleteProduct(p);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProducts() {
        service.deleteAllProducts();
        return ResponseEntity.noContent().build();
    }
}
