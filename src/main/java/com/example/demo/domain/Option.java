package com.example.demo.domain;

import com.example.demo.dto.OptionDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Option {

    @Id
    @Column(nullable = false)
    @JsonProperty("id")
    private int id;

    @Column(nullable = false)
    @JsonProperty("color")
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonProperty("size")
    private Size size;

    @Column(nullable = false)
    @JsonProperty("stock")
    private int stock;

    @JsonCreator
    public Option(@JsonProperty("id") int id, @JsonProperty("color") String color, @JsonProperty("size") Size size, @JsonProperty("stock") int stock) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.stock = stock;
    }

    public OptionDto of() {
        return new OptionDto(this.id, this.color, this.size, this.stock);
    }
}
