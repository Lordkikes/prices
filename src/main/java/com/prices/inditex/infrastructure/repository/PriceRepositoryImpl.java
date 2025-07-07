package com.prices.inditex.infrastructure.repository;

import com.prices.inditex.domain.model.PriceResponseDTO;
import com.prices.inditex.domain.repository.PriceRepository;
import com.prices.inditex.infrastructure.jpa.PriceJpaRepository;
import com.prices.inditex.infrastructure.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation of the PriceRepository interface.
 * Provides methods to interact with the database for retrieving price information.
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    /**
     * Repository for performing CRUD operations on PriceEntity.
     */
    private final PriceJpaRepository priceJPARepository;

    /**
     * Mapper for converting PriceEntity to PriceResponseDTO.
     */
    private final PriceMapper mapper;

    /**
     * Retrieves the preferred price for a given product, brand, and application date.
     * The preferred price is determined based on the highest priority within the specified date range.
     *
     * @param applicationDate The date and time for which the price is being queried.
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @return An Optional containing the preferred PriceResponseDTO, or an empty Optional if no price is found.
     */
    @Override
    public Optional<PriceResponseDTO> getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceJPARepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate
        ).map(mapper::toDomain);
    }
}