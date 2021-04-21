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

    @Autowired
    private CityRepository cityRepository;

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


        for (ProductQuantity p: customerOrder.getProducts()) {
            Product product = productRepository.getByName(p.getProduct().getName());
            System.out.println(product);

            p.setProduct(product);
            //  productRepository.save(product);
            productQuantityRepository.save(p);
        }


        orderRepository.save(customerOrder);
        return "Order added";
    }

    @GetMapping(path = "/all")
    public Iterable<CustomerOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}


