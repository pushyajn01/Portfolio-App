package com.project.portfolioapp.dto;

import java.util.List;

public class PortfolioInfoDTO {
    private List<HoldingsInfoDTO>holdingsInfoList;
    private double totalPortfolioValue;
    private double totalBuyPrice;
    private double profitLossPercent;

    public List<HoldingsInfoDTO> getHoldingsInfoList() {
        return holdingsInfoList;
    }

    public void setHoldingsInfoList(List<HoldingsInfoDTO> holdingsInfoList) {
        this.holdingsInfoList = holdingsInfoList;
    }

    public double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public double getProfitLossPercent() {
        return profitLossPercent;
    }

    public void setProfitLossPercent(double profitLossPercent) {
        this.profitLossPercent = profitLossPercent;
    }
}
