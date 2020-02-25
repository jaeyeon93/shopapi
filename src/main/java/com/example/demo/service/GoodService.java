package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.dto.GoodDto;
import com.example.demo.dto.OptionDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.GoodRepository;
import com.example.demo.repo.OptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Converter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodService {
    @Autowired
    private final GoodRepository goodRepository;

    @Autowired
    private final OptionRepository optionRepository;

    @Autowired
    private Converter converter;

//    @Transactional
//    public Good create(String input) throws JsonProcessingException {
//        GoodDto goodDto = converter.inputToGood(input);
//        Good good = goodDto.of();
//        System.out.println("" + good.toString());
//        return goodRepository.save(good);
//    }

    @Transactional
    public Good create(GoodDto goodDto) throws JsonProcessingException {
        Good good = goodDto.of();
        System.out.println("생성" + good.toString());
        return goodRepository.save(good);
    }

    public Good findById(long id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 물건이 존재하지 않습니다."));
        return good;
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
