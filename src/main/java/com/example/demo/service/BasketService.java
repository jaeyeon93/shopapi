package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Item;
import com.example.demo.dto.BasketDto;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.StockException;
import com.example.demo.repo.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketService {

    @Autowired
    private final BasketRepository basketRepository;

    @Autowired
    private final ItemService itemService;

    @Transactional
    public Basket createBasket() {
        Basket basket = new BasketDto().of();
        return basketRepository.save(basket);
    }

    @Transactional
    public Basket getBasketById(long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 장바구니를 찾지못합니다."));
        return basket;
    }

    @Transactional
    public List<Basket> findAll() {
        List<Basket> baskets = basketRepository.findAll();
        return baskets;
    }

    // 물건을 구매하면 buy, 그렇지 않고 장바구니에 추가하면 not
    public Basket buyOrNot(long userId, BasketInputDto basketInputDto) throws StockException {
        Item item = itemService.buyOrNotItem(basketInputDto);
        Basket basket = getBasketById(userId).update(item);
        return basketRepository.save(basket);
    }


}
