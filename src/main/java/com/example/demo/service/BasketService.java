package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.dto.BasketDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

    @Autowired
    private final BasketRepository basketRepository;

    public Basket createBasket() {
        Basket basket = new BasketDto().of();
        return basketRepository.save(basket);
    }

    public Basket findByUserId(long userId) {
        Basket basket = basketRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 장바구니를 찾지못합니다."));
        return basket;
    }
}
