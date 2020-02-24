package com.example.demo.controller;


import com.example.demo.domain.Good;
import com.example.demo.service.GoodService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class ApiGoodController {
    @Resource(name= "goodService")
    private GoodService goodService;

    @PostMapping("")
    public ResponseEntity<Good> create(@RequestBody Good input) {
        Good good = goodService.create(input);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/goods/" + good.getId()));
        return new ResponseEntity<Good>(headers, HttpStatus.CREATED);
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
