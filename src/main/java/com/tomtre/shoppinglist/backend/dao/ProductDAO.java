package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.Product;

import java.util.List;
import java.util.UUID;


public interface ProductDAO {

    List<Product> getProducts();

    void saveOrUpdateProduct(Product product);

    Product getProduct(UUID productId);

    void removeProduct(UUID productId);
}
