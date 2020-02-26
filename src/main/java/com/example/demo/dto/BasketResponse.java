package com.example.demo.dto;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Good;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BasketResponse {
    private Long id;
    private List<Good> goods;
    private int total_price;

    public static BasketResponse from(Basket basket) {
        return BasketResponse.builder()
                .id(basket.getId())
                .goods(basket.getGoods())
                .total_price(basket.getTotal_price())
                .build();
    }
}
