package com.example.demo.domain;

import com.example.demo.dto.GoodDto;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Good {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private int price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_option"))
    private List<Option> options = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Shipping shipping;

    public Good(String name, String provider, int price, Option option, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options.add(option);
        this.shipping = shipping;
    }

    public Good(String name, String provider, int price, List<Option> options, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }

    public GoodDto of() {
        return new GoodDto(this.name, this.provider, this.price, this.options, this.shipping);
    }

    public Long getId() {
        return id;
    }
}
