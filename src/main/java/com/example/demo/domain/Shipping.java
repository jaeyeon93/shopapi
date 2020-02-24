package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
public class Shipping {

    @Id
    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Boolean canBundle;

    public Shipping() {}

    public Shipping(Method method, Integer price, Boolean canBundle) {
        System.out.println("shipping is called");
        this.method = method;
        this.price = price;
        this.canBundle = canBundle;
    }
}
