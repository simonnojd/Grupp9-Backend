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

    @GetMapping(path = "/remove/{id}")
    public String  removeCategory(@PathVariable Long id){
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

    @PostMapping(path = "/delete")
    public String deleteCategoryById(@RequestBody Category category){
        categoryRepository.deleteById(category.getId());
        return "Kategorin är borttagen";
    }

}
