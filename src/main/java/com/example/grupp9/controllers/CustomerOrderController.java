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
@RequestMapping(path = "/order")
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
            Product product = productRepository.getById(p.getProduct().getId());
            product.setQuantity(product.getQuantity()-p.getQuantity());

            p.setProduct(product);
            productRepository.save(product);
            productQuantityRepository.save(p);
        }

        orderRepository.save(customerOrder);
        return "Order added";
    }

    @PostMapping(path = "/send+{id}")
    public String sendOrder(@PathVariable Long id){
        Optional<CustomerOrder> order = orderRepository.findById(id);
        if (order.isPresent()){
            order.get().setSent(true);
            orderRepository.save(order.get());
            return "Order skickad";
        }


    return "Order existerar inte";
    }


    @PostMapping(path = "/delete+{id}")
    public String deleteOrder(@PathVariable Long id){
        Optional<CustomerOrder> order = orderRepository.findById(id);
        if (order.isPresent()){
            orderRepository.deleteById(id);
            return "Order borttagen";
        }

        return "Order existerar inte";
    }


    @GetMapping(path = "/sentOrders")
    public List<CustomerOrder> sentOrders(){

        return orderRepository.findBySent(true);
    }

    @GetMapping(path = "/newOrders")
    public List<CustomerOrder> newOrders(){

        return orderRepository.findBySent(false);
    }

    @GetMapping(path = "/all")
    public Iterable<CustomerOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}


