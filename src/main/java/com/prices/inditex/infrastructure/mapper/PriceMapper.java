package com.prices.inditex.infrastructure.mapper;

import com.prices.inditex.domain.model.PriceResponseDTO;
import com.prices.inditex.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between PriceEntity and PriceResponseDTO.
 * Utilizes MapStruct for automatic mapping of fields.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Maps a PriceEntity object to a PriceResponseDTO object.
     *
     * @param entity The PriceEntity instance to be mapped.
     * @return A PriceResponseDTO instance containing the mapped data.
     */
    @Mapping(source = "brand.id", target = "brandId")
    PriceResponseDTO toDomain(PriceEntity entity);
}