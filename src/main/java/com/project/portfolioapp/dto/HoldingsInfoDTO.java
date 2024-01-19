package com.project.portfolioapp.dto;

public class HoldingsInfoDTO {
    private String stockId;
    private String stockName;
    private int quantity;
    private double buyPrice;
    private double gainLoss;

    public String getstockId() {
        return stockId;
    }

    public void setstockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getGainLoss() {
        return gainLoss;
    }

    public void setGainLoss(double gainLoss) {
        this.gainLoss = gainLoss;
    }
}
