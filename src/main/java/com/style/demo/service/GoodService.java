package com.style.demo.service;

import com.style.demo.domain.Good;
import com.style.demo.domain.Item;
import com.style.demo.dto.BasketInputDto;
import com.style.demo.dto.GoodDto;
import com.style.demo.exception.NotFoundException;
import com.style.demo.exception.StockException;
import com.style.demo.repo.GoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodService {

    @Autowired
    private final GoodRepository goodRepository;

    @Autowired
    private final OptionService optionService;

    @Transactional
    public Good create(GoodDto goodDto) {
        Good good = goodDto.of();
        return goodRepository.save(good);
    }

    @Transactional
    public Good getGoodById(long id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 물건이 존재하지 않습니다."));
        return good;
    }

    // 장바구니에서 물건을 구매. Good과 Option에서 GoodId, OptionId, 구매갯수, 물건가격, 배송가격을 측정한다.
    public Item buyOrCancelGood(BasketInputDto basketInputDto) throws StockException {
        Good good = getGoodById(basketInputDto.getGoodId());
        optionService.changeOption(basketInputDto.getOptionId(), basketInputDto.getCount(), basketInputDto.isFlag());
        Item item = new Item(basketInputDto.getGoodId(), basketInputDto.getOptionId(), basketInputDto.getCount(), good.getPrice(), good.getShipping().getPrice());
        log.info("아이템 생성 {}", item.toString());
        return item;
    }

    @Transactional
    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
