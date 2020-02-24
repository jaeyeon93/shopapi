package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
public class Option {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("size")
    private Size size;

    @Column(nullable = false)
    @JsonProperty("stock")
    private Integer stock;

    public Option() {}

    public Option(String color, Size size, Integer stock) {
        System.out.println("option is to called");
        this.color = color;
        this.size = size;
        this.stock = stock;
    };


}
