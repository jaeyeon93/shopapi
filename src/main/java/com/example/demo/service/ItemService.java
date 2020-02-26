package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private final GoodService goodService;

    @Autowired
    private final ItemRepository itemRepository;

    public Item buyOrCancelItem(BasketInputDto basketInputDto) {
        Item item = goodService.buyOrCancelGood(basketInputDto);
        return itemRepository.save(item);
    }
}
