package com.style.demo.dto;

import com.style.demo.domain.Basket;
import com.style.demo.domain.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BasketDto {
    private List<Item> items = new ArrayList<>();
    private int total_price;
    public BasketDto(List<Item> items, int total_price) {
        this.items = items;
        this.total_price = total_price;
    }

    public Basket of() {
        return new Basket(this.items, this.total_price);
    }
}
