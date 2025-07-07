package com.prices.inditex.infrastructure.mapper;

import com.prices.inditex.domain.model.PriceResponseDTO;

import com.prices.inditex.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    @Mapping(source = "brand.id", target = "brandId")
    PriceResponseDTO toDomain(PriceEntity entity);
}
