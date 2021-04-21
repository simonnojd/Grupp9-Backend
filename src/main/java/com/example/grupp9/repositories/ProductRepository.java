package com.example.grupp9.repositories;

import com.example.grupp9.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product getByName (String name);
}
