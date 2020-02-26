package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.dto.BasketInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final GoodService goodService;

    public Item buyOrCancelItem(BasketInputDto basketInputDto) {

    }
}
