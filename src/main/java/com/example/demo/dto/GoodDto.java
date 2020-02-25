package com.example.demo.dto;

import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.domain.Shipping;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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

    public Good of() {
        return new Good(this.name, this.provider, this.price, this.options, this.shipping);
    }
}
