package com.example.demo.service;

import com.example.demo.domain.Option;
import com.example.demo.dto.OptionDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public Option changeOption(int optionId, int count, boolean buyflag) {
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new NotFoundException("해당 옵션이 존재하지 않습니다."));
        return calculateStock(option, count, buyflag);
    }

    // 여기서 재고의 수량변경
    @Transactional
    public Option calculateStock(Option option, int count, boolean buyflag) {
        OptionDto optionDto = option.of();
        int stock = optionDto.getStock();
        if (buyflag) {
            stock -= count;
            optionDto.setStock(stock);
            return optionDto.of();
        }
        stock += count;
        optionDto.setStock(stock);
        return optionDto.of();
    }
}
