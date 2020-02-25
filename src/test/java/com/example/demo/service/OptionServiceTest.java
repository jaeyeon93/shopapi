package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.domain.Option;
import com.example.demo.dto.Converter;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.GoodRepository;
import com.example.demo.repo.OptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OptionServiceTest {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private OptionService optionService;

    @Autowired
    private Converter converter;

    private Good good;
    private Option option;

    @Before
    public void setUp() throws JsonProcessingException {
        good = converter.inputToGood(input).of();
        goodRepository.save(good);
        option = optionRepository.findById(1001)
                .orElseThrow(() -> new NotFoundException("해당옵션을 찾을 수 없습니다."));
    }

    @Test
    public void 옵션이정상적으로들어갔는지() {
        log.info(option.toString());
        assertThat(option.getStock(), is(10));
    }

    @Test
    public void 물건구매할때재고변화() {
        option = optionService.changeOption(1001, 2, true);
        assertThat(option.getStock(), is(8));
    }

    @Test
    public void 물건취소할때의재고변화() {
        option = optionService.changeOption(1001, 2, false);
        assertThat(option.getStock(), is(12));
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
