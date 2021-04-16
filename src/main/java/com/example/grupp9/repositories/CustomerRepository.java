package com.example.grupp9.repositories;

import com.example.grupp9.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
