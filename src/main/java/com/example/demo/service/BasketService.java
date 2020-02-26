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
    @Transactional
    public Basket buyOrAddGood(long userId, BasketInputDto basketInputDto) throws StockException {
        Item item = itemService.buyOrAddItem(basketInputDto);
        Basket basket = getBasketById(userId).update(item);
        log.info("서비스 레이어에서 {}", basket.toString());
        return basketRepository.save(basket);
    }

    // 장바구니에서 물건제거
    @Transactional
    public Basket removeItem(long userId, BasketInputDto basketInputDto) {
        Basket basket = getBasketById(userId);
        List<Item> items = basket.getItems();
        for (Item item : items)
            if (item.getOptionId() == basketInputDto.getOptionId()) {
                Item temp = checkRemove(item, basketInputDto.getCount());
                if (temp.getCount() == 0) {
                    items.remove(item);
                    break;
                }
        }
        return basketRepository.save(basket);
    }

    public Item checkRemove(Item item, int count) {
        int left = item.getCount() - count;
        if (left > 0)
            item.setCount(left);
        else
            item.setCount(0);
        return item;
    }

    public Item getItemByOptionId(List<Item> items, int optionId) {
        for (Item item : items)
            if (item.getOptionId() == optionId)
                return item;
        throw new NotFoundException("옵션이 없습니다.");
    }
}
