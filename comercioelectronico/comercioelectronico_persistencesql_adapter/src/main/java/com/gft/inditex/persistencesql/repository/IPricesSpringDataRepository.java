package com.gft.inditex.persistencesql.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.gft.inditex.persistencesql.entity.PriceJpaEntity;

@Component
public interface IPricesSpringDataRepository extends JpaRepository<PriceJpaEntity, Integer>{

	@Query("SELECT p FROM PriceJpaEntity p WHERE (p.startDate<= :date AND p.endDate >= :date) AND p.productId = :productId AND p.brandId = :brandId")
	List<PriceJpaEntity> findPricesProductByDate(
								@Param("date") LocalDateTime date,
								@Param("productId") Integer productId,
								@Param("brandId") Integer brandId);
	
}
