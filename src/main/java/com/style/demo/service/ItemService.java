package com.style.demo.service;

import com.style.demo.domain.Item;
import com.style.demo.dto.BasketInputDto;
import com.style.demo.exception.StockException;
import com.style.demo.repo.ItemRepository;
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

    public Item buyOrAddItem(BasketInputDto basketInputDto) throws StockException {
        Item item = goodService.buyOrCancelGood(basketInputDto);
        return itemRepository.save(item);
    }
}
