package com.style.demo.dto;

import com.style.demo.domain.Good;
import com.style.demo.domain.Option;
import com.style.demo.domain.Shipping;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GoodDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("price")
    private int price;

    @JsonProperty("options")
    private List<Option> options = new ArrayList<>();

    @JsonProperty("shipping")
    private Shipping shipping;

    @JsonCreator
    public GoodDto(@JsonProperty("name") String name, @JsonProperty("provider") String provider, @JsonProperty("price") Integer price, @JsonProperty("options") List<Option> options, @JsonProperty("shipping") Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }

    public GoodDto(String name, String provider, int price, List<Option> options, Shipping shipping) {
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.options = options;
        this.shipping = shipping;
    }

    public Good of() {
        return new Good(this.name, this.provider, this.price, this.options, this.shipping);
    }

}
