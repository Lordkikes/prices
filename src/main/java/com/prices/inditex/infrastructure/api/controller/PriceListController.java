package com.prices.inditex.infrastructure.api.controller;

import com.prices.inditex.application.service.PriceService;
import com.prices.inditex.infrastructure.api.dto.response.PriceListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Precios", description = "API para consultar precios de productos")
@RestController
@RequestMapping("/prices")
public class PriceListController {

    private PriceService priceService;

    @Autowired
    public PriceListController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price/{brandId}/{productId}/{date}")
    @Operation(
            summary = "Obtener precio de producto",
            description = "Devuelve el precio vigente para un producto, marca y fecha dados",
            parameters = {
                    @Parameter(name = "brandId", description = "ID de la marca", example = "1", required = true),
                    @Parameter(name = "productId", description = "ID del producto", example = "35455", required = true),
                    @Parameter(name = "date", description = "Fecha de consulta (yyyy-MM-dd'T'HH:mm:ss)", example = "2020-06-14T10:00:00", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Precio encontrado",
                            content = @Content(schema = @Schema(implementation = PriceListResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Precio no encontrado"
                    )
            }
    )
    public PriceListResponse getPrice(
            @PathVariable(name = "brandId") int brandId,
            @PathVariable(name = "productId") int productId,
            @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date
    ) {
        return PriceListResponse.fromDomain(priceService.get(brandId, productId, date));
    }
}