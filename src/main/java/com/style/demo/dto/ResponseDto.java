package com.style.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDto<T> {
    private int status;
    private String message;
    private T response;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ResponseDto(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public static ResponseDto of(HttpStatus httpStatus, String message) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto(status, message, null);
    }

    public static <T> ResponseDto<T> of(HttpStatus httpStatus, String message, T reponse) {
        int status = Optional.ofNullable(httpStatus)
                .orElse(httpStatus.OK)
                .value();
        return new ResponseDto<>(status, message, reponse);
    }
}
