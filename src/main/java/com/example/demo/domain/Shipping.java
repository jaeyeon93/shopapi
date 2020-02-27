package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JsonProperty("method")
    private Method method;

    @Column(nullable = false)
    @JsonProperty("price")
    private int price;

    @Column(nullable = false)
    @JsonProperty("canBundle")
    private boolean canBundle;

    @JsonCreator
    public Shipping(@JsonProperty("method") Method method, @JsonProperty("price") int price, @JsonProperty("canBundle") boolean canBundle) {
        this.method = method;
        this.price = price;
        this.canBundle = canBundle;
    }

    public Shipping(Method method, int price, Boolean canBundle) {
        System.out.println("shipping is called");
        this.method = method;
        this.price = price;
        this.canBundle = canBundle;
    }
}
