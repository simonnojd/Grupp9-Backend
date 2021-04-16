package com.example.grupp9.controllers;

import com.example.grupp9.models.*;
import com.example.grupp9.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
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

    @GetMapping(path = "/add/{productId}+{customerId}+{quantity}")
    public String addOrder(@PathVariable List<Long> productId, @PathVariable Long customerId, @PathVariable List<Integer>quantity) {
        List<ProductQuantity> productQuantityList = new ArrayList<>();
        CustomerOrder order = new CustomerOrder();
        for (int i = 0; i < productId.size(); i++) {
            Optional<Product> product = productRepository.findById(productId.get(i));
            if (product.isEmpty()) {
                return "Failed.";
            }
            ProductQuantity productQuantity = new ProductQuantity(product.get(), quantity.get(i));
            productQuantityList.add(productQuantity);
            productQuantityRepository.save(productQuantity);
        }
        order.setProducts(productQuantityList);
        Optional<Customer> customer = customerRepository.findById(customerId);
       /* if (customer.isPresent()) {
            order.setCustomer(customer.get());
        }

        */
        //else order.setCustomer(new Customer());
        orderRepository.save(order);
        return "Order added";
    }

    @GetMapping(path = "/all")
    public Iterable<CustomerOrder> getAllOrder() {
        return orderRepository.findAll();
    }

}


