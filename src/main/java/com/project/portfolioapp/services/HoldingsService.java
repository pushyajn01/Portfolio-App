package com.project.portfolioapp.services;

import com.project.portfolioapp.dto.HoldingsDTO;
import com.project.portfolioapp.model.Holdings;

import java.util.List;
import java.util.Optional;

public interface HoldingsService {
    Optional<Holdings>getHoldings(Long userId, String stockId);
     void updateHoldings(HoldingsDTO holdingsDTO);
     List<Holdings> getHoldingsByUserId(Long userId);
}
