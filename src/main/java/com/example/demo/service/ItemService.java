package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Option;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.StockException;
import com.example.demo.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private final GoodService goodService;

    @Autowired
    private final ItemRepository itemRepository;

    public Item buyOrAddItem(BasketInputDto basketInputDto) throws StockException {
        Item item = goodService.buyOrCancelGood(basketInputDto);
        return itemRepository.save(item);
    }
}
