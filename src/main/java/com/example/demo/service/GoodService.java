package com.example.demo.service;

import com.example.demo.domain.Good;
import com.example.demo.domain.Item;
import com.example.demo.dto.BasketInputDto;
import com.example.demo.dto.GoodDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.StockException;
import com.example.demo.repo.GoodRepository;
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
        return item;
    }

    @Transactional
    public List<Good> findAll() {
        return goodRepository.findAll();
    }
}
