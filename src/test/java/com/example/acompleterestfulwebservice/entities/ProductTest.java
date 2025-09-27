package com.example.acompleterestfulwebservice.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Autowired
    private Validator validator;

    @Test
    void invalidProduct() {

        // 1. Crear una instancia del producto con datos inválidos
        Product product = new Product(" ", new BigDecimal("-10.00"));

        // 2. Invocar manualmente al validador
        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        // 3. Verificar que se encontraron el número esperado de errores
        assertEquals(2, violations.size());

        // 4. (Opcional) Imprimir las violaciones para depurar
        violations.forEach(System.out::println);
    }
}