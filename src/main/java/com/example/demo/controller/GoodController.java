package com.example.demo.controller;


import com.example.demo.domain.Good;
import com.example.demo.dto.GoodDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.GoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {
    @Autowired
    private GoodService goodService;

    @PostMapping
    public ResponseDto createGood(@RequestBody GoodDto goodDto) throws JsonProcessingException {
        Good good = goodService.create(goodDto);
        return ResponseDto.of(HttpStatus.CREATED, "상품 생성에 성공했습니다.", good);
    }

    @GetMapping("/{id}")
    public ResponseDto getGood(@PathVariable long id) {
        Good good = goodService.findById(id);
        return ResponseDto.of(HttpStatus.OK, "상품 조회에 성공했습니다.", good);
    }

    @GetMapping
    public List<Good> getGoods() {
        List<Good> goods = goodService.findAll();
        return goods;
    }
}
