package com.example.demo.dto;

import com.example.demo.domain.Option;
import com.example.demo.domain.Shipping;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class GoodDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("options")
    private List<Option> options = new ArrayList<>();

    @JsonProperty("shipping")
    private Shipping shipping;

    public GoodDto(String name, String provider, Integer price, Option option, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options.add(option);
        this.shipping = shipping;
    }

    public GoodDto(String name, String provider, Integer price, List<Option> options, Shipping shipping) {
        System.out.println("Good constructor called");
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }
}
