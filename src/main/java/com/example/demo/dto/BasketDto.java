package com.example.demo.dto;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Good;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BasketDto {
    private List<Good> goods = new ArrayList<>();

    public BasketDto(List<Good> goods) {
        this.goods = goods;
    }
    public Basket of() {
        return new Basket();
    }
}
