package com.style.demo.service;

import com.style.demo.domain.Basket;
import com.style.demo.domain.Good;
import com.style.demo.domain.Option;
import com.style.demo.dto.BasketInputDto;
import com.style.demo.dto.Converter;
import com.style.demo.dto.GoodDto;
import com.style.demo.exception.StockException;
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
        basketService.buyOrAddGood(1, basketInputDto);
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
        Basket result = basketService.buyOrAddGood(1, new BasketInputDto(1, 1002, 2, false));
        log.info("결과 : {}", result.toString());
        assertThat(result.getTotalPrice(), is(60000));
        Option option = optionService.getOptionByOptionId(1002);
        assertThat(option.getStock(), is(10));
    }

    @Test
    public void 물건구매하기() throws StockException {
        basket = basketService.getBasketById(1);
        basketService.buyOrAddGood(1, new BasketInputDto(1, 1001, 1, true));
        Option option = optionService.getOptionByOptionId(1001);
        assertThat(option.getStock(), is(9));
    }

    @Test
    public void 물건제거하기() throws Exception {
        basket = basketService.getBasketById(1);
        basketService.buyOrAddGood(1, new BasketInputDto(1, 1001, 1, false));
        basketService.buyOrAddGood(1, new BasketInputDto(1, 1003, 3, false));
        basketService.removeItem(1, new BasketInputDto(1, 1003, 2));
        Basket result = basketService.getBasketById(1);
        log.info("테스트 코드 실행 후 {}", basket.toString());
        assertThat(result.getItems().size(), is(3));
        assertThat(basketService.getItemByOptionId(result.getItems(), 1003).getCount(), is(1));
    }

    @Test
    public void 물건제거하기_아이템속성이제거된다() throws StockException {
        basket = basketService.getBasketById(1);
        basketService.buyOrAddGood(1, new BasketInputDto(1, 1002, 2, false));
        basketService.removeItem(1, new BasketInputDto(1, 1002, 2));
        Basket result = basketService.getBasketById(1);
        log.info("아이템속성 제거 : {}", result.toString());
        assertThat(result.getItems().size(), is(1));
    }

    private String input = "{\n" +
            "\t\"name\": \"구매제거 테스트중\",\n" +
            "\t\"provider\": \"StyleShare\",\n" +
            "\t\"price\": 20000,\n" +
            "\t\"options\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1001,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"S\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1002,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"M\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1003,\n" +
            "\t\t\t\"color\": \"yellow\",\n" +
            "\t\t\t\"size\": \"L\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1004,\n" +
            "\t\t\t\"color\": \"blue\",\n" +
            "\t\t\t\"size\": \"S\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1005,\n" +
            "\t\t\t\"color\": \"blue\",\n" +
            "\t\t\t\"size\": \"M\",\n" +
            "\t\t\t\"stock\": 10\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"optionId\": 1006,\n" +
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
