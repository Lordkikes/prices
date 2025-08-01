package com.prices.inditex.infrastructure.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class PriceRequestDto {

    @NotNull(message = "Product ID cannot be null")
    @Schema(example = "35455")
    private Long productId;

    @NotNull(message = "Brand ID cannot be null")
    @Schema(example = "1")
    private Long brandId;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(example = "2020-06-14T10:00:00")
    private LocalDateTime date;
}
