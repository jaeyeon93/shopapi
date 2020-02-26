package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.dto.BasketDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    @Autowired
    private final BasketRepository basketRepository;

    public Basket createBasket() {
        Basket basket = new BasketDto().of();
        return basketRepository.save(basket);
    }

    public Basket findById(long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 장바구니를 찾지못합니다."));
        return basket;
    }

    public List<Basket> findAll() {
        List<Basket> baskets = basketRepository.findAll();
        return baskets;
    }
}
