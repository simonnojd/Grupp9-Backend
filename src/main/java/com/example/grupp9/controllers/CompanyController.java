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

    @Autowired
    private ProductRepository productRepository;

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


    @PostMapping(path = "/update")
    public String updateCompany(@RequestBody Company c) {
        Optional<Company> company = companyRepository.findById(c.getId());

        if (company.isPresent()){
            companyRepository.save(c);
            return "Företaget har uppdaterats";
        }
        else return "Företaget finns ej i databasen";
    }

   /* @PostMapping(path = "/delete+{id}")
    public String deleteCompanyById(@PathVariable Long id){
        Optional<Company> company = companyRepository.findById(id);
       if(company.isPresent()){
           for (Product p : productRepository.findAll()) {
               if (p.getCompany() == company.get())
                   p.setCompany(null);
           }
           companyRepository.deleteById(id);
           return "Företaget är borttaget";
       }
       else return  "Företaget finns inte";

    }*/

}
