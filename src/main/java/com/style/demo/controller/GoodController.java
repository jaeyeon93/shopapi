package com.style.demo.controller;


import com.style.demo.domain.Good;
import com.style.demo.dto.GoodDto;
import com.style.demo.dto.ResponseDto;
import com.style.demo.service.GoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {

    @Autowired
    private final GoodService goodService;

    @PostMapping
    public ResponseDto createGood(@RequestBody GoodDto goodDto) throws JsonProcessingException {
        Good good = goodService.create(goodDto);
        return ResponseDto.of(HttpStatus.CREATED, "상품 생성에 성공했습니다.", good);
    }

    @GetMapping("/{id}")
    public ResponseDto getGood(@PathVariable long id) {
        Good good = goodService.getGoodById(id);
        return ResponseDto.of(HttpStatus.OK, "상품 조회에 성공했습니다.", good);
    }

    @GetMapping
    public List<Good> getGoods() {
        List<Good> goods = goodService.findAll();
        return goods;
    }
}
