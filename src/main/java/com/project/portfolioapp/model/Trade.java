package com.project.portfolioapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TradeId;
    @Column
    private String tradeType;
    @Column
    private int Quantity;
    @Column
    private double BuyPrice;
    @Column
    private String stockId;

    @Column
    private Long userId;
    public Trade() {
    }

    public Trade(Long tradeId, String tradeType, int quantity, double buyPrice, String stockId, Long userId) {
        TradeId = tradeId;
        this.tradeType = tradeType;
        Quantity = quantity;
        BuyPrice = buyPrice;
        this.stockId = stockId;
        this.userId = userId;
    }

    public Long getTradeId() {
        return TradeId;
    }

    public void setTradeId(Long tradeId) {
        TradeId = tradeId;
    }

    public String gettradeType() {
        return tradeType;
    }

    public void settradeType(String TradeType) {
        tradeType = TradeType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        BuyPrice = buyPrice;
    }

    public String getstockId() {
        return stockId;
    }

    public void setstockId(String stockId) {
        this.stockId = stockId;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "TradeId=" + TradeId +
                ", TradeType='" + tradeType + '\'' +
                ", Quantity=" + Quantity +
                ", BuyPrice=" + BuyPrice +
                ", stock=" + stockId +
                ", user=" + userId +
                '}';
    }
}
