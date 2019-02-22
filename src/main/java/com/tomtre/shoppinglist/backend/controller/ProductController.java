package com.tomtre.shoppinglist.backend.controller;

import com.tomtre.shoppinglist.backend.entity.Product;
import com.tomtre.shoppinglist.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "list-products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
            productService.saveProduct(product);
        } else {
            productService.updateProduct(product);
        }
        return "redirect:/product/list";
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam("productId") UUID productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("productNotFound", product == null);
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("productId") UUID productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}
