package com.example.demo.controller;

import com.example.demo.domain.Basket;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.dto.BasketResponse;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketController {

    @Autowired
    private final BasketService basketService;

    // 장바구니생성
    @PostMapping
    public ResponseDto createBasket() {
        Basket basket = basketService.createBasket();
        return ResponseDto.of(HttpStatus.CREATED, "장바구니를 생성했습니다.", basket);
    }

    // id로 장바구니 조회
    @GetMapping("/{id}")
    public ResponseDto getBasket(@PathVariable long id) {
        Basket basket = basketService.getBasketById(id);
        return ResponseDto.of(HttpStatus.OK, "장바구니 조회에 성공했습니다.", basket);
    }

    // 장바구니조회
    @GetMapping
    public ResponseDto<List<BasketResponse>> getBaskets() {
        List<BasketResponse> basketList = basketService.findAll().stream()
                .map(BasketResponse::from)
                .collect(Collectors.toList());
        return ResponseDto.of(HttpStatus.OK, "모든 장바구니 조회 성공", basketList);
    }

    // 물건구매
    @PutMapping("/{userId}/buy")
    public ResponseDto buyGood(@PathVariable long userId, @RequestBody BasketInputDto basketInputDto) {
        Basket basket = basketService.buyOrCancelItem(userId, basketInputDto);
        return ResponseDto.of(HttpStatus.OK, "물건구매 성공", basket);
    }


}
