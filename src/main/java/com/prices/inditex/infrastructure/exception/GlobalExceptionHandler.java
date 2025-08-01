package com.prices.inditex.infrastructure.exception;

import com.prices.inditex.infrastructure.api.dto.response.PriceListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<PriceListResponse> handlePriceNotFoundException(IllegalArgumentException exception) {
        return ResponseEntity.notFound().build();
    }
}