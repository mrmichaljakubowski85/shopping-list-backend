package com.tomtre.shoppinglist.backend.service;

import com.tomtre.shoppinglist.backend.dao.ProductDAO;
import com.tomtre.shoppinglist.backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    @Transactional
    public void saveOrUpdateProduct(Product product) {
        productDAO.saveOrUpdateProduct(product);
    }

    @Override
    @Transactional
    public Product getProduct(UUID productId) {
        return productDAO.getProduct(productId);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        productDAO.removeProduct(productId);
    }

}
