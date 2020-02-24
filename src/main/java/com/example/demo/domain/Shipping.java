package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
public class Shipping {

    @Id
    @Enumerated(EnumType.STRING)
    @JsonProperty("method")
    private Method method;

    @Column(nullable = false)
    @JsonProperty("price")
    private Integer price;

    @Column(nullable = false)
    @JsonProperty("canBundle")
    private Boolean canBundle;

    public Shipping() {}

    public Shipping(Method method, Integer price, Boolean canBundle) {
        System.out.println("shipping is called");
        this.method = method;
        this.price = price;
        this.canBundle = canBundle;
    }
}
