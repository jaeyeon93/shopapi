package com.style.demo.domain;

import com.style.demo.dto.BasketDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
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
        log.info("장바구니에서 아이템 추가 : {}", item.toString());
        this.items.add(item);
        return this;
    }

    public int getTotalPrice() {
        int sum = 0;
        for (Item item : items)
            sum += ((item.getCount() * item.getPrice()) + item.getShippingPrice());
        return sum;
    }

    public BasketDto of() {
        return new BasketDto(this.items, this.totalPrice);
    }
}
