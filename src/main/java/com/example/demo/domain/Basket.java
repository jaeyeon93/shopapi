package com.example.demo.domain;

import com.example.demo.dto.BasketDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Good> goods = new ArrayList<>();

    @Column
    @ColumnDefault("0")
    private int total_price;

    public Basket update(Good good) {
        this.goods.add(good);
        return this;
    }

    public BasketDto of() {
        return new BasketDto(this.goods);
    }
}
