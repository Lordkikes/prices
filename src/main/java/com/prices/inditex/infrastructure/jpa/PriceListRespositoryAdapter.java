package com.prices.inditex.infrastructure.jpa;


import com.prices.inditex.domain.model.PriceList;
import com.prices.inditex.domain.port.out.PriceListRepositoryPort;
import com.prices.inditex.infrastructure.entity.PriceListEntity;
import com.prices.inditex.infrastructure.jpa.repository.PriceListRepository;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for accessing price data from the database.
 * Extends JpaRepository to provide CRUD operations for PriceEntity.
 */
public class PriceListRespositoryAdapter implements PriceListRepositoryPort {

    private PriceListRepository priceListRepository;

    public PriceListRespositoryAdapter(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @Override
    public PriceList get(int productId, int brandId, LocalDateTime dateTime) {
        List<PriceListEntity> prices = priceListRepository.findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(brandId, productId, dateTime, dateTime);
        return prices.size() != 0 ? prices.get(0).toDomain() : null;
    }

}