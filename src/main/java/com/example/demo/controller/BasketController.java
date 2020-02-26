package com.example.demo.controller;

import com.example.demo.domain.Basket;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketController {

    @Autowired
    private final BasketService basketService;

    @PostMapping
    public ResponseDto createBasket() {
        Basket basket = basketService.createBasket();
        return ResponseDto.of(HttpStatus.CREATED, "장바구니를 생성했습니다.", basket);
    }
}
