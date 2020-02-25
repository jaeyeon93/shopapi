package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.dto.GoodDto;
import com.example.demo.repo.GoodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Converter;

import java.util.List;

@Service
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private Converter converter;

    public Good create(String input) throws JsonProcessingException {
        GoodDto goodDto = converter.inputToGood(input);
        return goodRepository.save(goodDto.of());
    }

    public Good findById(long id) {
        return goodRepository.findById(id).get();
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
