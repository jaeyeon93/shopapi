package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
public class Good {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private List<Option> options = new ArrayList<>();

    @Column(nullable = false)
    private Shipping shipping;

    public Good() {}

    public Good(String name, String provider, Integer price, Option option, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options.add(option);
        this.shipping = shipping;
    }

    public Good(String name, String provider, Integer price, List<Option> options, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }
}
