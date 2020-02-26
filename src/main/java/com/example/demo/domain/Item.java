package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long goodId;

    @Column
    private int optionId;

    @Column
    private int count;

    @Column
    private int price;

    public Item(long goodId, int optionId, int count, int price) {
        this.goodId = goodId;
        this.optionId = optionId;
        this.count = count;
        this.price = price;
    }
}
