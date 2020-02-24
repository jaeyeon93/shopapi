package com.example.demo.dto;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.example.demo.domain.Good;
import com.example.demo.domain.Method;
import com.example.demo.domain.Shipping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonToGoodTest {
    private String input = "{\n" +
            "\t\"name\": \"구매제거 테스트중\",\n" +
            "\t\"provider\": \"StyleShare\",\n" +
            "\t\"price\": 20000,\n" +
            "\t\"options\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1001,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"S\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1002,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"M\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1003,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"L\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1004,\n" +
            "\t\t\t\"color\": \"blue\",\n" +
            "\t\t\t\"size\": \"S\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1005,\n" +
            "\t\t\t\"color\": \"blue\",\n" +
            "\t\t\t\"size\": \"M\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"id\": 1006,\n" +
            "\t\t\t\"color\": \"blue\",\n" +
            "\t\t\t\"size\": \"L\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"shipping\": {\n" +
            "\t\"method\": \"FREE\",\n" +
            "\t\"price\": 0,\n" +
            "\t\"canBundle\": true\n" +
            "\t}\n" +
            "}";

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
    }

    @Test
    public void convertTest() throws JsonProcessingException {
        Good good = mapper.readValue(input, Good.class);
        assertThat(good.getOptions().size(), is(6));
        assertThat(good.getShipping().getMethod(), is(Method.FREE));
        assertThat(good.getPrice(), is(20000));
        System.out.println(instanceOf(good.getClass()));
    }

    @Test
    public void shipping() throws JsonProcessingException {
        String shipInput = "{\n" +
                "\t\"method\": \"FREE\",\n" +
                "\t\"price\": 0,\n" +
                "\t\"canBundle\": true\n" +
                "\t}";
    }
}
