package com.foodpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cont_id")
    private long id;

    @Column(name = "cont_email")
    private String email;

    @Column(name = "cont_mob")
    private String mobile;

    @Column(name = "cont_twi")
    private String twitter;






}
