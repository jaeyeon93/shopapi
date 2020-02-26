package com.example.demo.service;

import com.example.demo.domain.Basket;
import com.example.demo.domain.Good;
import com.example.demo.dto.BasketDto;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.dto.GoodDto;
import com.example.demo.exception.NotFoundException;
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
    private final GoodService goodService;

    public Basket createBasket() {
        Basket basket = new BasketDto().of();
        return basketRepository.save(basket);
    }

    public Basket findById(long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 장바구니를 찾지못합니다."));
        return basket;
    }

    public List<Basket> findAll() {
        List<Basket> baskets = basketRepository.findAll();
        return baskets;
    }

    public Basket buyGood(long userId, BasketInputDto basketInputDto) {
        log.info("buyGood 메서드에서 전달받은 basketInputDto : {}", basketInputDto.toString());
        Good good = goodService.buyOrCancelGood(basketInputDto);
        BasketDto basketDto = findById(userId).of();
        basketDto.getGoods().add(good);
        log.info("저장되기 직전의 basketDto : {}", basketDto.toString());
        return basketRepository.save(basketDto.of());
    }
}
