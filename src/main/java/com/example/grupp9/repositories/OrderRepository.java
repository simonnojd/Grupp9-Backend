package com.example.grupp9.repositories;

import com.example.grupp9.models.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
}

