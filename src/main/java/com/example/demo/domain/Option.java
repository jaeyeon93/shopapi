package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
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
    private int stock;

    public Option(String color, Size size, int stock) {
        System.out.println("option is to called");
        this.color = color;
        this.size = size;
        this.stock = stock;
    };


}
