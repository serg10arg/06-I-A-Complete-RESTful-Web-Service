package com.example.acompleterestfulwebservice.config;

import com.example.acompleterestfulwebservice.dao.ProductRepository;
import com.example.acompleterestfulwebservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AppInit implements CommandLineRunner {

    private final ProductRepository productRepository;

    public AppInit(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            Product p1 = new Product("Producto A", BigDecimal.valueOf(29.99));
            Product p2 = new Product("Producto B", BigDecimal.valueOf(45.50));
            Product p3 = new Product("Producto C", BigDecimal.valueOf(100.00));

            // Guardar los productos en la base de datos en una sola operación
            productRepository.saveAll(List.of(p1,p2,p3));
        }
    }
}
