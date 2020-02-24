package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@ToString
@Getter
public class Shipping {

    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Boolean canBundel;

    public Shipping() {}

    public Shipping(Method method, Integer price, Boolean canBundel) {
        this.method = method;
        this.price = price;
        this.canBundel = canBundel;
    }
}
