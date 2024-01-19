package com.project.portfolioapp.services;

import com.project.portfolioapp.dto.HoldingsDTO;
import com.project.portfolioapp.dto.TradeDTO;
import com.project.portfolioapp.model.Holdings;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.model.Trade;
import com.project.portfolioapp.repository.HoldingsRepository;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService{
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired private StockRepository stockRepository;
    @Autowired
    private HoldingsService holdingsService;

    @Autowired
    private HoldingsRepository holdingsRepository;
    @Override
    public void processTrade(TradeDTO tradeDTO)
    {
        if (tradeDTO.getUserId() == null || tradeDTO.getStockId() == null ||
                tradeDTO.getQuantity() <= 0 || tradeDTO.getTradeType() == null) {
            throw new IllegalArgumentException("Invalid trade parameters");
        }
        Stock stock = stockRepository.findByStockId(tradeDTO.getStockId());
        if (stock == null) {
            throw new IllegalArgumentException("Stock not found");
        }

        if ("SELL".equals(tradeDTO.getTradeType())) {
            List<Trade> buyTrades = tradeRepository.findByUserIdAndStockIdAndTradeType(
                    tradeDTO.getUserId(), tradeDTO.getStockId(), "BUY");

            int totalBuyQuantity = buyTrades.stream().mapToInt(Trade::getQuantity).sum();

            // Ensure that the user has bought the stock
            if (totalBuyQuantity <= 0) {
                throw new IllegalArgumentException("User has not bought the specified stock");
            }

            // Ensure that the user is not trying to sell more than the bought quantity
            if (tradeDTO.getQuantity() > totalBuyQuantity) {
                throw new IllegalArgumentException("Cannot sell more than bought quantity");
            }
        }

        Trade trade = new Trade();
        trade.setuserId(tradeDTO.getUserId());
        trade.setstockId(tradeDTO.getStockId());
        trade.setQuantity(tradeDTO.getQuantity());
        trade.settradeType(tradeDTO.getTradeType());

        // Process BUY trade
        if ("BUY".equals(tradeDTO.getTradeType())) {
            trade.setBuyPrice(stock.getOpenPrice());
        }
        updateHoldings(tradeDTO, stock);
        // Save the trade
        tradeRepository.save(trade);
    }
private void updateHoldings(TradeDTO tradeDTO,Stock stock)
{
    Optional<Holdings>existingHoldings=holdingsService.getHoldings(tradeDTO.getUserId(),tradeDTO.getStockId());
    if(existingHoldings.isPresent())
    {

        Holdings holdings=existingHoldings.get();

        if("BUY".equals(tradeDTO.getTradeType()))
        {
           int totalQuantity=holdings.getquantity()+tradeDTO.getQuantity();
            double averagebuyprice= (holdings.getBuyPrice()* holdings.getquantity()+ tradeDTO.getQuantity()*stock.getOpenPrice())/totalQuantity;
            holdings.setquantity(totalQuantity);
            holdings.setBuyPrice(averagebuyprice);
        } else if ("SELL".equals(tradeDTO.getTradeType())) {
           int totalQuantity=holdings.getquantity()-tradeDTO.getQuantity();
            holdings.setquantity(totalQuantity);
        }
        //write for sell
        holdingsRepository.save(holdings);
    }
    //make new holding
    else {
        HoldingsDTO newHoldingsDTO = new HoldingsDTO();
        newHoldingsDTO.setUserId(tradeDTO.getUserId());
        newHoldingsDTO.setStockId(tradeDTO.getStockId());
        newHoldingsDTO.setQuantity(tradeDTO.getQuantity());
        newHoldingsDTO.setAverageBuyPrice(stock.getOpenPrice());

        holdingsService.updateHoldings(newHoldingsDTO);

    }
}

}
