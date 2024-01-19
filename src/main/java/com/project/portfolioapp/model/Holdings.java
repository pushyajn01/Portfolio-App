package com.project.portfolioapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="holdings")
public class Holdings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HoldingId;
    @Column
    private Long userId;
    @Column
    private String stockId;
    @Column
    private int quantity;
    @Column
    private double BuyPrice;

    public Holdings() {
    }

    public Holdings(Long holdingId, Long userId, String stockId, int quantity, double buyPrice) {
        HoldingId = holdingId;
        this.userId = userId;
        this.stockId = stockId;
        this.quantity = quantity;
        BuyPrice = buyPrice;
    }

    public Long getHoldingId() {
        return HoldingId;
    }

    public void setHoldingId(Long holdingId) {
        HoldingId = holdingId;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getstockId() {
        return stockId;
    }

    public void setstockId(String stockId) {
        this.stockId = stockId;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        BuyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return "Holdings{" +
                "HoldingId=" + HoldingId +
                ", UserId=" + userId +
                ", StockId='" + stockId + '\'' +
                ", Quantity=" + quantity +
                ", BuyPrice=" + BuyPrice +
                '}';
    }
}
