package com.example.demo.domain;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class Option {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    @Column(nullable = false)
    private Integer stock;

    public Option() {}

    public Option(String color, Size size, Integer stock) {
        this.color = color;
        this.size = size;
        this.stock = stock;
    };


}
