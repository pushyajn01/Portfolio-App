package com.project.portfolioapp.repository;

import com.project.portfolioapp.model.Holdings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoldingsRepository extends JpaRepository<Holdings,Long> {
    Optional<Holdings> findByUserIdAndStockId(Long userId, String stockId);
    List<Holdings> findByUserId(Long userId);
}
