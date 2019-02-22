package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.RestServiceDescriptor;
import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.exception.ProductNotFoundException;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RestServiceDescriptor.FULL_PATH)
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{productId}")
    public Product product(@PathVariable UUID productId) {
        Product product = productService.getProduct(productId);
        if (product == null)
            throw new ProductNotFoundException("Product ID not found: " + productId);
        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
            productService.saveProduct(product);
        } else {
            productService.updateProduct(product);
        }
        return product;
    }
}
