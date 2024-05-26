package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name  = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;

    @ManyToOne
    @Column(name = "order_cust")
    private User customer;

    @JsonIgnore
    @ManyToOne
    @Column(name = "order_rest")
    private Restaurant restaurant;

    @Column(name = "order_totalAmount")
    private long totalAmount;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_createdAt")
    private Date createdAt;

    @JsonIgnore
    @ManyToOne
    @Column(name = "order_deliveryAddress")
    private Address deliveryAddress;

    @OneToMany
    @Column(name = "order_items")
    private List<OrderItem> items;

//    @Column(name = "order_paymentMethod")
//    private Payment payment;

    @Column(name = "order_totalItem")
    private int totalItem;

    @Column(name = "order_totalPrice")
    private int totalPrice;

}
