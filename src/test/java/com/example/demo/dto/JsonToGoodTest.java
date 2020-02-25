package com.example.demo.dto;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.example.demo.domain.Good;
import com.example.demo.domain.Method;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
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


    @Autowired
    private Converter converter;


    @Test
    public void convertTest() throws JsonProcessingException {
        GoodDto goodDto = converter.inputToGood(input);
        assertThat(goodDto.getName(), is("구매제거 테스트중"));
        assertThat(goodDto.getOptions().size(), is(6));
        assertThat(goodDto.getShipping().getMethod(), is(Method.FREE));
        assertThat(goodDto.getPrice(), is(20000));
    }
}
