package com.example.grupp9.controllers;

import com.example.grupp9.models.Category;
import com.example.grupp9.models.Product;
import com.example.grupp9.repositories.CategoryRepository;
import com.example.grupp9.repositories.CompanyRepository;
import com.example.grupp9.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/add/{name}")
    public String addCategory(@PathVariable String name) {
        Category category = categoryRepository.findByName(name);
        if (category != null)
            return "Category Exist";
        category = new Category(name);
        categoryRepository.save(category);
        return "Category added";
    }

    @GetMapping(path = "/all")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/remove/{id}")
    public String  removeCategory(@PathVariable Long id){
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return "Removed category";
    }

}
