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
@RequestMapping(path = "/company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping(path = "/add")
    public String addCategory(@RequestBody  Company c) {
        Company company = companyRepository.findByName(c.getName());
        if (company != null)
            return "Category Exist";
        companyRepository.save(c);
        return "company added";
    }

    @GetMapping(path = "/all")
    public Iterable<Company> getAllCategories() {
        return companyRepository.findAll();
    }

    @GetMapping(path = "/remove/{id}")
    public String  removeCategory(@PathVariable Long id){
        Company company = companyRepository.findById(id).get();
        companyRepository.delete(company);
        return "Removed company";
    }

    @PostMapping(path = "/update")
    public String updateCompany(@RequestBody Company c) {
        Optional<Company> company = companyRepository.findById(c.getId());

        if (company.isPresent()){
            companyRepository.save(c);
            return "Företaget har uppdaterats";
        }
        else return "Företaget finns ej i databasen";
    }

}
