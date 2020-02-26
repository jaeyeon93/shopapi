package com.example.demo.service;

import com.example.demo.domain.Option;
import com.example.demo.dto.OptionDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.StockException;
import com.example.demo.repo.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptionService {
    private final OptionRepository optionRepository;

    @Transactional
    public Option changeOption(int optionId, int count, boolean buyflag) throws StockException {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new NotFoundException("해당 옵션이 존재하지 않습니다."));
        return optionRepository.save(calculateStock(option, count, buyflag));
    }

    // 만약에 물건을 구마해면 buyflag=true 그렇지 않고 장바구니에 담으면 buyflag = false;
    public Option calculateStock(Option option, int count, boolean buyflag) throws StockException {
        OptionDto optionDto = option.of();
        if (buyflag) {
            int stock = optionDto.getStock();
            stock -= count;
            if (stock <= 0)
                throw new StockException("물건의 제고가 부족합니다.");
            log.info("물건을 구매하고 난 뒤의 stock : {}", stock);
            optionDto.setStock(stock);
        }
        return optionDto.of();
    }

    // 옵션아이디로 가져오기
    @Transactional
    public Option getOptionById(int optionId) {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new NotFoundException("해당 옵션을 찾을 수 없습니다."));
        return option;
    }
}
