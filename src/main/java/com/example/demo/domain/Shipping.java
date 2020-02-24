package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Shipping {

    @Enumerated(EnumType.STRING)
    private Method method;

    private Integer price;
    private Boolean canBundel;

    public Shipping() {}

    public Shipping(Method method, Integer price, Boolean canBundel) {
        this.method = method;
        this.price = price;
        this.canBundel = canBundel;
    }
}
