package com.example.demo.integration;

import com.example.demo.domain.Basket;
import com.example.demo.service.BasketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BasketApiTest {
    @Mock
    private BasketService basketService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc.perform(post("/baskets"))
                .andExpect(status().isOk());
    }

    @Test
    public void 생성된장바구니조회() {
        Basket basket = basketService.findByUserId(0);
        System.out.println(basket.toString());
        assertThat(basket.getId(), is(1));
    }
}
