package com.style.demo.dto;

import com.style.demo.domain.Option;
import com.style.demo.domain.Size;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class OptionDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("color")
    private String color;

    @Enumerated(EnumType.STRING)
    @JsonProperty("size")
    private Size size;

    @JsonProperty("stock")
    private int stock;

    @JsonCreator
    public OptionDto(@JsonProperty("id") int id, @JsonProperty("color") String color, @JsonProperty("size") Size size, @JsonProperty("stock") int stock) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.stock = stock;
    };

    public Option of() {
        return new Option(this.id, this.color, this.size, this.stock);
    }
}
