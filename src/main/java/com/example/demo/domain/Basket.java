package com.example.demo.domain;

import com.example.demo.dto.BasketDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Good> goods = new ArrayList<>();

    public BasketDto of() {
        return new BasketDto(this.goods);
    }
}
