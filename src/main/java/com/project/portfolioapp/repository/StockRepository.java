package com.project.portfolioapp.repository;

import com.project.portfolioapp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
    Stock findByStockId(String stockId);
}
