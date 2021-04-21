package com.example.grupp9.controllers;

import com.example.grupp9.models.Product;
import com.example.grupp9.repositories.CompanyRepository;
import com.example.grupp9.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(path = "/add/{name}+{price}+{companyId}")
    public String addProduct(@PathVariable String name, @PathVariable Double price, @PathVariable long companyId) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCompany(companyRepository.findById(companyId).get());
        productRepository.save(product);
        return "Product added";
    }

    @GetMapping(path = "/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
