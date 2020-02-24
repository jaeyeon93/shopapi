package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.domain.Shipping;
import com.example.demo.repo.GoodRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodService {
    @Resource(name = "goodRepository")
    private GoodRepository goodRepository;

    public Good create(Good input) {
        Shipping shipping = input.getShipping();
        List<Option> options = input.getOptions();
        Good good new Good(input.getName(), input.getProvider(), input.getPrice(), options, shipping);
        return goodRepository.save(good);
    }

    public Good findById(long id) {
        return goodRepository.findById(id).get();
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
