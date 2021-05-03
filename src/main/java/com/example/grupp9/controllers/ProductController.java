package com.example.grupp9.controllers;

import com.example.grupp9.models.Category;
import com.example.grupp9.models.Company;
import com.example.grupp9.models.Product;
import com.example.grupp9.repositories.CategoryRepository;
import com.example.grupp9.repositories.CompanyRepository;
import com.example.grupp9.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "/add")
    public String addProduct(@RequestBody Product p) {
        Category category = categoryRepository.findByName(p.getCategory().getName());
        Company company = companyRepository.findByName(p.getCompany().getName());

        if (company == null)
            return p.getCompany().getName() + " Företaget existerar ej i databasen.";
        else if (category == null)
            return p.getCategory().getName() + " Kategorin existerar ej i databasen.";

        p.setCategory(category);
        p.setCompany(company);

        productRepository.save(p);
        return "Produkt tillagd";
    }

    @PostMapping(path = "/update")
    public String updateProduct(@RequestBody Product p) {
        Optional<Product> product = productRepository.findById(p.getId());
        Category category = categoryRepository.findByName(p.getCategory().getName());
        Company company = companyRepository.findByName(p.getCompany().getName());

        if (company == null)
            return p.getCompany().getName() + " Företaget existerar ej i databasen.";
        else if (category == null)
            return p.getCategory().getName() + " Kategorin existerar ej i databasen.";

        if (product.isPresent()){
            productRepository.save(p);
            return "Produkten har uppdaterats";
        }
        else return "Produkten finns ej i databasen";
    }

    @PostMapping(path = "/delete+{id}")
    public String deleteProductById(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            product.get().setActive(false);
            productRepository.save(product.get());
            return "Produkten är borttagen från hemsidan";
        }
        return "Produkten finns ej.";
    }

    @GetMapping(path = "/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/all/active")
    public Iterable<Product> getAllActiveProducts() {
        return productRepository.findAllByActive(true);
    }

    @GetMapping(path = "/all/notActive")
    public Iterable<Product> getAllNotActiveProducts() {
        return productRepository.findAllByActive(false);
    }

    @GetMapping(path = "/{categoryName}")
    public Iterable<Product> getProductsByCategory(@PathVariable String categoryName) {
        Category category = categoryRepository.findByName(categoryName);

        Iterable<Product> findAllProducts = productRepository.findAllByActive(true);
        List<Product> productList = new ArrayList<>();
        for (Product p : findAllProducts) {
            if (category.getId().equals(p.getCategory().getId())){
                productList.add(p);
            }
        }
        return productList;
    }



}
