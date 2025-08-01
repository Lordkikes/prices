package com.prices.inditex.infrastructure.jpa.repository;

import com.prices.inditex.infrastructure.entity.PriceListEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface PriceListRepository extends JpaRepository<PriceListEntity, Integer> {

    List<PriceListEntity> findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(int brandId, int productId, LocalDateTime startDate, LocalDateTime endDate);

}