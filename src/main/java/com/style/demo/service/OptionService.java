package com.style.demo.service;

import com.style.demo.domain.Option;
import com.style.demo.dto.OptionDto;
import com.style.demo.exception.NotFoundException;
import com.style.demo.exception.StockException;
import com.style.demo.repo.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptionService {
    private final OptionRepository optionRepository;

    @Transactional
    public Option changeOption(int optionId, int count, boolean buyflag) throws StockException {
        Option option = optionRepository.findByOptionId(optionId)
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
    public Option getOptionByOptionId(int optionId) {
        Option option = optionRepository.findByOptionId(optionId)
                .orElseThrow(() -> new NotFoundException("해당 옵션을 찾을 수 없습니다."));
        return option;
    }
}
