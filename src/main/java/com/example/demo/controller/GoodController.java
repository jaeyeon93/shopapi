package com.example.demo.controller;


import com.example.demo.domain.Good;
import com.example.demo.service.GoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    @Resource(name= "goodService")
    private GoodService goodService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody String input) throws JsonProcessingException {
        Good good = goodService.create(input);
        HttpHeaders headers = new HttpHeaders();
        System.out.println("good in controller " + good.toString());
        headers.setLocation(URI.create("/goods/" + good.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public Good getGood(@PathVariable long id) {
        Good good = goodService.findById(id);
        return good;
    }

    @GetMapping("")
    public List<Good> getGoods() {
        List<Good> goods = goodService.findAll();
        return goods;
    }
}
