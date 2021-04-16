package com.example.grupp9.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OutOfStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName = "id")
    private Product product;
    private Date date;

    public OutOfStock() {
    }

    public OutOfStock(Product product, Date date) {
        this.product = product;
        this.date = date;
    }

    @Override
    public String toString() {
        return "OutOfStock{" +
                "id=" + id +
                ", product=" + product +
                ", date=" + date +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
