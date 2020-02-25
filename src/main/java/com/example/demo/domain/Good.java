package com.example.demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
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

    @OneToMany(targetEntity = Option.class, fetch = FetchType.LAZY)
    @JsonProperty("options")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_option"))
    private List<Option> options = new ArrayList<>();

    @OneToOne(targetEntity = Shipping.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("shipping")
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
        System.out.println("Good constructor called");
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }

    public Long getId() {
        return id;
    }
}
