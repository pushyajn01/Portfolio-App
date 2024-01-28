package com.project.portfolioapp.dto;

public class TradeDTO {
    private Long userId;
    private String stockId;
    private int quantity;
    private String tradeType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public TradeDTO() {
    }

    public TradeDTO(Long userId, String stockId, int quantity, String tradeType) {
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.tradeType = tradeType;
    }
}
