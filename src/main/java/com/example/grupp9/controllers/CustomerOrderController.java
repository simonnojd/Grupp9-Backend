package com.example.grupp9.controllers;

import com.example.grupp9.models.*;
import com.example.grupp9.repositories.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductQuantityRepository productQuantityRepository;

    @PostMapping(path = "/add")
    public String addOrder(@RequestBody CustomerOrder customerOrder) {
        System.out.println(customerOrder);

        City city = new City(customerOrder.getCustomer().getCity().getName());
        cityRepository.save(city);

        Customer customer = new Customer();
        customer.setFirstName(customerOrder.getCustomer().getFirstName());
        customer.setLastName(customerOrder.getCustomer().getLastName());
        customer.setCity(city);
        customer.setZipCode(customerOrder.getCustomer().getZipCode());

        customerRepository.save(customer);

        customerOrder.setCustomer(customer);


        orderRepository.save(customerOrder);
        return "Order added";
    }

    @GetMapping(path = "/all")
    public Iterable<CustomerOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}


