package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Good;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.dto.Converter;
import com.example.demo.dto.GoodDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class BasketServiceTest {

    @Autowired
    private BasketService basketService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private Converter converter;

    private BasketInputDto basketInputDto;

    @Before
    public void setUp() throws Exception {
        GoodDto goodDto = converter.inputToGood(input);
        goodService.create(goodDto);
        basketService.createBasket();
        basketInputDto = new BasketInputDto(1, 1001, 1, true);
        basketService.buyOrCancelItem(1, basketInputDto);
        assertThat(basketInputDto.getOptionId(), is(1001));
    }

    @Test
    public void findGood() {
        Good good = goodService.findById(1);
        assertThat(good.getPrice(), is(20000));
    }

    @Test
    public void 물건추가하기() {
        Basket basket = basketService.findById(1);
        assertThat(basket.getTotalPrice(), is(20000));
        Basket result = basketService.buyOrCancelItem(1, new BasketInputDto(1, 1002, 2, true));
        log.info("result : {}", result.toString());
        assertThat(result.getTotalPrice(), is(60000));
    }

//    @Test
//    public void 물건제거하기() {
//        Basket basket = basketService.findById(1);
//        assertThat(basket.getTotalPrice(), is(20000));
//
//    }

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
}
