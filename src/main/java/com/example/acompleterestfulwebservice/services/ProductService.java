package com.example.acompleterestfulwebservice.services;

import com.example.acompleterestfulwebservice.dao.ProductRepository;
import com.example.acompleterestfulwebservice.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void initializerDatabase() {}

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product p) {
        productRepository.delete(p);
    }

    public void deleteAllProducts() {
        productRepository.deleteAllInBatch();
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductsByMinPrice(double minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(BigDecimal.valueOf(minPrice));
    }

    @Transactional(readOnly = true)
    public long countProducts() {
        return productRepository.count();
    }

}
