package com.project.portfolioapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="stock")
public class Stock {
    @Id
    private String stockId;
    @Column
    private String StockName;
    @Column
    private double OpenPrice;
    @Column
    private double ClosePrice;
    @Column
    private double HighPrice;
    @Column
    private double LowPrice;
    @Column
    private double LastPrice;


    public Stock(String stockId, String stockName, double openPrice, double closePrice, double lowPrice, double highPrice, double lastPrice) {
        this.stockId=stockId;
        this.StockName=stockName;
        this.OpenPrice=openPrice;
        this.ClosePrice=closePrice;
        this.LowPrice=lowPrice;
        this.HighPrice=highPrice;
        this.LastPrice=lastPrice;
    }

    public Stock() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return StockName;
    }

    public void setStockName(String stockName) {
        StockName = stockName;
    }

    public double getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(double openPrice) {
        OpenPrice = openPrice;
    }

    public double getClosePrice() {
        return ClosePrice;
    }

    public void setClosePrice(double closePrice) {
        ClosePrice = closePrice;
    }

    public double getHighPrice() {
        return HighPrice;
    }

    public void setHighPrice(double highPrice) {
        HighPrice = highPrice;
    }

    public double getLowPrice() {
        return LowPrice;
    }

    public void setLowPrice(double lowPrice) {
        LowPrice = lowPrice;
    }

    public double getLastPrice() {
        return LastPrice;
    }

    public void setLastPrice(double lastPrice) {
        LastPrice = lastPrice;
    }



    @Override
    public String toString() {
        return "Stock{" +
                "stockId='" + stockId + '\'' +
                ", StockName='" + StockName + '\'' +
                ", OpenPrice=" + OpenPrice +
                ", ClosePrice=" + ClosePrice +
                ", HighPrice=" + HighPrice +
                ", LowPrice=" + LowPrice +
                ", LastPrice=" + LastPrice +
                '}';
    }
}
