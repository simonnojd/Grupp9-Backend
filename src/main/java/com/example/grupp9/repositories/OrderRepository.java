package com.example.grupp9.repositories;

import com.example.grupp9.models.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {
    Iterable<CustomerOrder> findBySent (boolean b);
}

