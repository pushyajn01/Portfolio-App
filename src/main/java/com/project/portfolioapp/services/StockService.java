package com.project.portfolioapp.services;

import com.project.portfolioapp.dto.StockDTO;
import com.project.portfolioapp.model.Stock;
import org.springframework.stereotype.Service;

import java.io.InputStream;


public interface StockService {
    void updateStocks(InputStream fileInputStream);
    Stock getStockById(String stockId);

}
