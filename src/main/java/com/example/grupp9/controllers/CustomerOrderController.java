package com.example.grupp9.controllers;

import com.example.grupp9.models.*;
import com.example.grupp9.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @PostMapping(path = "/add")
    public String addOrder(@RequestBody CustomerOrder customerOrder) {

        orderRepository.save(customerOrder);
        return "Order added";
    }

    @GetMapping(path = "/all")
    public Iterable<CustomerOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}


