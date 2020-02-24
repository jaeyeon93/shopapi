package com.example.demo.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
    public static Good inputToGood(String jsonInput) throws JsonProcessingException {
        return getMapper().readValue(jsonInput, Good.class);
    };

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
