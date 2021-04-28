package com.example.grupp9.controllers;

import com.example.grupp9.models.Category;
import com.example.grupp9.models.Company;
import com.example.grupp9.models.Product;
import com.example.grupp9.repositories.CategoryRepository;
import com.example.grupp9.repositories.CompanyRepository;
import com.example.grupp9.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/add")
    public String addCategory(@RequestBody  Category c) {
        Category category = categoryRepository.findByName(c.getName());
        if (category != null)
            return "Category Exist";
        categoryRepository.save(c);
        return "Category added";
    }

    @GetMapping(path = "/all")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @GetMapping(path = "/all/active")
    public Iterable<Category> getAllActiveCategory() {
        return categoryRepository.findAllByActive(true);
    }

    @GetMapping(path = "/all/notActive")
    public Iterable<Category> getAllNotActiveCategory() {
        return categoryRepository.findAllByActive(false);
    }

    @GetMapping(path = "/delete+{id}")
    public String  deleteCategory(@PathVariable Long id){
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return "Removed category";
    }

    @PostMapping(path = "/update")
    public String updateCategory(@RequestBody Category c) {
        Optional<Category> category = categoryRepository.findById(c.getId());

        if (category.isPresent()){
            categoryRepository.save(c);
            return "Kategorin har uppdaterats";
        }
        else return "Kategorin finns ej i databasen";
    }

    @PostMapping(path = "/delete+{id}")
    public String deleteCategoryById(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            category.get().setActive(false);
            categoryRepository.save(category.get());
            return "Kategorin är borttagen från hemsidan";
        }
        return "Kategorin finns ej.";
    }

}
