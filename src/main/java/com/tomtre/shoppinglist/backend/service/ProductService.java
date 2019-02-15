package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProducts();

    void saveOrUpdateProduct(Product product);

    Product getProduct(UUID productId);

    void deleteProduct(UUID productId);
}
