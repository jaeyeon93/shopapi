package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.domain.Shipping;
import com.example.demo.repo.GoodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Converter;
import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodService {
    @Resource(name = "goodRepository")
    private GoodRepository goodRepository;

    public Good create(String input) throws JsonProcessingException {
        Good good = Converter.inputToGood(input);
        System.out.println("good in service : " + good.toString());
        return goodRepository.save(good);
    }

    public Good findById(long id) {
        return goodRepository.findById(id).get();
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
