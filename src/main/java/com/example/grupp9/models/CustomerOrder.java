package com.example.grupp9.models;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name="ProductOrderMap", joinColumns = {@JoinColumn(referencedColumnName = "id")}
    , inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private List<ProductQuantity> productsQuantity;
    private Date date;
    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "id")
    private Customer customer;

    private boolean sent;



    public CustomerOrder() {
        this.sent = false;
    }

    public CustomerOrder(List<ProductQuantity> products, Date date, Customer customer) {
        this.productsQuantity = products;
        this.date = date;
        this.customer = customer;
        this.sent = false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + productsQuantity +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }

    public List<ProductQuantity> getProducts() {
        return productsQuantity;
    }

    public void addProduct(ProductQuantity product) {
        productsQuantity.add(product);
    }

    public void setProducts(List<ProductQuantity> products) {
        this.productsQuantity = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
