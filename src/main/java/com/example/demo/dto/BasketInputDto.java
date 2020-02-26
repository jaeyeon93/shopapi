package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BasketInputDto {

    @JsonProperty("goodId")
    private long goodId;

    @JsonProperty("optionId")
    private int optionId;

    @JsonProperty("count")
    private int count;

    @JsonProperty("buyflag")
    private boolean buyflag;

    @JsonCreator
    public BasketInputDto(@JsonProperty("goodId") long goodId, @JsonProperty("optoinId") int optionId, @JsonProperty("count") int count, @JsonProperty("buyflag") boolean buyflag) {
        this.goodId = goodId;
        this.optionId = optionId;
        this.buyflag = buyflag;
        this.count = count;
    }
}
