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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
    public void 모든장바구니조회() throws Exception {
        mockMvc.perform(get("/baskets"))
                .andExpect(status().isOk());
    }

    @Test
    public void 생성된장바구니조회() throws Exception {
        mockMvc.perform(get("/baskets/1"))
                .andExpect(status().isOk());
    }
}
