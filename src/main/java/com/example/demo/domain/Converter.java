package com.example.demo.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Converter {
    public Good inputToGood(String jsonInput) throws JsonProcessingException {
        return getMapper().readValue(jsonInput, Good.class);
    };

    @Bean
    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
