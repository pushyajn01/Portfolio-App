package com.project.portfolioapp.dto;

public class StockDTO {
    private String stockId;
    private String StockName;
    private double OpenPrice;
    private double ClosePrice;
    private double HighPrice;
    private double LowPrice;
    private double LastPrice;

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

    public StockDTO() {
    }

    public StockDTO(String stockId, String stockName, double openPrice, double closePrice, double highPrice, double lowPrice, double lastPrice) {
        this.stockId = stockId;
        StockName = stockName;
        OpenPrice = openPrice;
        ClosePrice = closePrice;
        HighPrice = highPrice;
        LowPrice = lowPrice;
        LastPrice = lastPrice;
    }
}
