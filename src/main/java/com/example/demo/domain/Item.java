package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private long goodId;

    private int optionId;

    private int count;

    private int price;

}
