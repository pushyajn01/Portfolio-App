package com.project.portfolioapp.controllers;

import com.project.portfolioapp.dto.HoldingsInfoDTO;
import com.project.portfolioapp.dto.PortfolioInfoDTO;
import com.project.portfolioapp.model.Holdings;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.services.HoldingsService;
import com.project.portfolioapp.services.StockService;
import com.project.portfolioapp.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/holdings")
public class HoldingsController {
    @Autowired
    private HoldingsService holdingsService;

    @Autowired
    private StockService stockService;
    @Autowired
    private TradeService tradeService;
    @GetMapping("/getHoldings/{userId}")
    public ResponseEntity<PortfolioInfoDTO> getPortfolioInfo(@PathVariable Long userId)
    {
        List<Holdings> holdingsList = holdingsService.getHoldingsByUserId(userId);
        if(holdingsList.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        List<HoldingsInfoDTO>holdingsInfoList=new ArrayList<>();
        double totalPortfolioValue = 0.0;
        double initialPortfolioValue = 0.0;
        double totalBuyPrice=0.0;
        for (Holdings holdings : holdingsList) {
            Stock stock = stockService.getStockById(holdings.getstockId());
            double currentPrice = stock.getLastPrice(); // Assuming you have a getLastPrice method in your StockService

            double buyPrice = holdings.getBuyPrice();
            int quantity = holdings.getquantity();
            double gainLoss = (currentPrice - buyPrice);

            HoldingsInfoDTO holdingsInfo = new HoldingsInfoDTO();
            holdingsInfo.setstockId(holdings.getstockId());
            holdingsInfo.setStockName(stock.getStockName());
            holdingsInfo.setQuantity(quantity);
            holdingsInfo.setBuyPrice(buyPrice);
            holdingsInfo.setGainLoss(gainLoss);

            holdingsInfoList.add(holdingsInfo);
            totalPortfolioValue+=quantity*currentPrice;
            initialPortfolioValue+=quantity*buyPrice;
            totalBuyPrice+=buyPrice;
        }
        double profitLossPercent=((totalPortfolioValue - initialPortfolioValue) / initialPortfolioValue * 100);
        PortfolioInfoDTO portfolioInfo = new PortfolioInfoDTO();
        portfolioInfo.setHoldingsInfoList(holdingsInfoList);
        portfolioInfo.setTotalPortfolioValue(totalPortfolioValue);
        portfolioInfo.setTotalBuyPrice(totalBuyPrice);
        portfolioInfo.setProfitLossPercent(profitLossPercent);

        return ResponseEntity.ok(portfolioInfo);
    }
}
