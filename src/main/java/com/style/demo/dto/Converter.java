package com.style.demo.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public GoodDto inputToGood(String jsonInput) throws JsonProcessingException {
        return getMapper().readValue(jsonInput, GoodDto.class);
    };

    @Bean
    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
