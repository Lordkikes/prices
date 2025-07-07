package com.prices.inditex.infrastructure.repository;

import com.prices.inditex.domain.model.PriceResponseDTO;

import com.prices.inditex.domain.repository.PriceRepository;
import com.prices.inditex.infrastructure.jpa.PriceJpaRepository;
import com.prices.inditex.infrastructure.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJPARepository;
    private final PriceMapper mapper;

    @Override
    public Optional<PriceResponseDTO> getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceJPARepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate
        ).map(mapper::toDomain);
    }
}