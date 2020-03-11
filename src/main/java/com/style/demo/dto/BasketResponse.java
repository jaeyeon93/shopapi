package com.style.demo.dto;

import com.style.demo.domain.Basket;
import com.style.demo.domain.Item;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BasketResponse {
    private Long id;
    private List<Item> items;
    private int total_price;

    public static BasketResponse from(Basket basket) {
        return BasketResponse.builder()
                .id(basket.getId())
                .items(basket.getItems())
                .total_price(basket.getTotalPrice())
                .build();
    }
}
