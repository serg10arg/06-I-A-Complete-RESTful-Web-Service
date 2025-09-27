package com.example.acompleterestfulwebservice.dao;

import com.example.acompleterestfulwebservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(BigDecimal minPrice);
}
