package com.example.demo.domain;

import com.example.demo.dto.BasketDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    @Column
    @ColumnDefault("0")
    private int totalPrice;

    public Basket(List<Item> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Basket update(Item item) {
        this.items.add(item);
        return this;
    }

    public int getTotalPrice() {
        int sum = 0;
        for (Item item : items)
            sum += item.getCount() * item.getPrice();
        return sum;
    }

    public BasketDto of() {
        return new BasketDto(this.items, this.totalPrice);
    }
}
