package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("provider")
    private String provider;

    @Column(nullable = false)
    @JsonProperty("price")
    private Integer price;

    @OneToMany(targetEntity = Option.class)
    @JsonProperty("options")
    private List<Option> options = new ArrayList<>();

    @OneToOne(targetEntity = Shipping.class)
    @JsonProperty("shipping")
    private Shipping shipping;

    public Good(String name, String provider, Integer price, Option option, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options.add(option);
        this.shipping = shipping;
    }

    public Good(String name, String provider, Integer price, List<Option> options, Shipping shipping) {
        System.out.println("Good constructor called");
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }
}
