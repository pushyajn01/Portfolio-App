package com.project.portfolioapp.repository;

import com.project.portfolioapp.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findByUserIdAndStockIdAndTradeType(Long UserId, String StockId, String TradeType);

}
