package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.dto.Converter;
import com.example.demo.dto.GoodDto;
import com.example.demo.exception.StockException;
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
    private OptionService optionService;

    @Autowired
    private Converter converter;

    private BasketInputDto basketInputDto;
    private Basket basket;

    @Before
    public void setUp() throws Exception {
        GoodDto goodDto = converter.inputToGood(input);
        goodService.create(goodDto);
        basketService.createBasket();
        basketInputDto = new BasketInputDto(1, 1001, 1, false);
        basketService.buyOrNot(1, basketInputDto);
        assertThat(basketInputDto.getOptionId(), is(1001));
    }

    @Test
    public void findGood() {
        Good good = goodService.getGoodById(1);
        assertThat(good.getPrice(), is(20000));
    }

    @Test
    public void 물건추가하기() throws StockException {
        basket = basketService.getBasketById(1);
        assertThat(basket.getTotalPrice(), is(20000));
        Basket result = basketService.buyOrNot(1, new BasketInputDto(1, 1002, 2, false));
        assertThat(result.getTotalPrice(), is(60000));
        Option option = optionService.getOptionById(1002);
        assertThat(option.getStock(), is(10));
    }

    @Test
    public void 물건구매하기() throws StockException {
        basket = basketService.getBasketById(1);
        basketService.buyOrNot(1, new BasketInputDto(1, 1001, 1, true));
        Option option = optionService.getOptionById(1001);
        assertThat(option.getStock(), is(9));
    }

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
