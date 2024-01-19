package com.project.portfolioapp.services;

import com.project.portfolioapp.dto.HoldingsDTO;
import com.project.portfolioapp.model.Holdings;
import com.project.portfolioapp.repository.HoldingsRepository;
import com.project.portfolioapp.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HoldingsServiceImpl implements HoldingsService{
    @Autowired
    private HoldingsRepository holdingsRepository;
@Autowired
    private TradeRepository tradeRepository;

@Override
    @Transactional
    public void updateHoldings(HoldingsDTO holdingsDTO)
{
    Optional<Holdings> existingHoldings = holdingsRepository.findByUserIdAndStockId(
            holdingsDTO.getUserId(), holdingsDTO.getStockId());

    if (existingHoldings.isPresent()) {
        // Update existing holdings
        Holdings holdings = existingHoldings.get();
        int totalQuantity = holdingsDTO.getQuantity();
        double totalValue = holdingsDTO.getAverageBuyPrice();

        holdings.setquantity(totalQuantity);
        holdings.setBuyPrice(totalValue);

        holdingsRepository.save(holdings);
    } else {
        // Create new holdings
        Holdings newHoldings = new Holdings();
        newHoldings.setuserId(holdingsDTO.getUserId());
        newHoldings.setstockId(holdingsDTO.getStockId());
        newHoldings.setquantity(holdingsDTO.getQuantity());
        newHoldings.setBuyPrice(holdingsDTO.getAverageBuyPrice());

        holdingsRepository.save(newHoldings);
    }
}
    @Override
    public Optional<Holdings> getHoldings(Long userId, String stockId) {
        return holdingsRepository.findByUserIdAndStockId(userId, stockId);
    }
    @Override
    public List<Holdings> getHoldingsByUserId(Long userId)
    {
        return holdingsRepository.findByUserId(userId);
    }
}
