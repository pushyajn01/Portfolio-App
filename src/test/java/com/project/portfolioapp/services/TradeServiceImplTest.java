package com.project.portfolioapp.services;

import com.project.portfolioapp.dto.TradeDTO;
import com.project.portfolioapp.model.Holdings;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.model.Trade;
import com.project.portfolioapp.repository.HoldingsRepository;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TradeServiceImplTest {
    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private HoldingsRepository holdingsRepository;

    @Mock
    private HoldingsService holdingsService;

    @InjectMocks
    private TradeServiceImpl tradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // Make sure this line is present
    }
    @Test
    public void testProcessTrade_BuyTrade_Success() {
        TradeDTO tradeDTO = createBuyTradeDTO();
        Stock stock = createStock();
        Holdings holdings = createHoldings();

        when(stockRepository.findByStockId(tradeDTO.getStockId())).thenReturn(stock);
        when(tradeRepository.findByUserIdAndStockIdAndTradeType(
                tradeDTO.getUserId(), tradeDTO.getStockId(), "BUY")).thenReturn(createBuyTrades());
        when(holdingsService.getHoldings(tradeDTO.getUserId(), tradeDTO.getStockId())).thenReturn(Optional.of(holdings));

        tradeService.processTrade(tradeDTO);

        // Add assertions based on your requirements
        verify(holdingsRepository, times(1)).save(any(Holdings.class));
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    @Test
    public void testProcessTrade_SellTrade_Success() {
        TradeDTO tradeDTO = createSellTradeDTO();
        Stock stock = createStock();
        Holdings holdings = createHoldings();


        when(stockRepository.findByStockId(tradeDTO.getStockId())).thenReturn(stock);
        when(tradeRepository.findByUserIdAndStockIdAndTradeType(
                tradeDTO.getUserId(), tradeDTO.getStockId(), "BUY")).thenReturn(createBuyTrades());
        when(holdingsService.getHoldings(tradeDTO.getUserId(), tradeDTO.getStockId())).thenReturn(Optional.of(holdings));

        tradeService.processTrade(tradeDTO);

        // Add assertions based on your requirements
        verify(holdingsRepository, times(1)).save(any(Holdings.class));
        verify(tradeRepository, times(1)).save(any(Trade.class));
    }

    private TradeDTO createBuyTradeDTO() {
        // Create and return a valid BUY TradeDTO for testing
        // Adjust the details based on your requirements
        TradeDTO tradeDTO=new TradeDTO();
        tradeDTO.setTradeType("BUY");
        tradeDTO.setQuantity(4);
        tradeDTO.setStockId("PQ123");
        tradeDTO.setUserId(6L);
        return tradeDTO;
    }

    private TradeDTO createSellTradeDTO() {
        // Create and return a valid SELL TradeDTO for testing
        // Adjust the details based on your requirements
        TradeDTO tradeDTO=new TradeDTO();
        tradeDTO.setTradeType("SELL");
        tradeDTO.setQuantity(3);
        tradeDTO.setStockId("PQ123");
        tradeDTO.setUserId(6L);
        return tradeDTO;
    }

    private Stock createStock() {
        // Create and return a valid Stock for testing
        // Adjust the details based on your requirements
        Stock stock=new Stock();
        stock.setStockId("PQ123");
        stock.setStockName("NewStock");
        stock.setClosePrice(5);
        stock.setOpenPrice(3);
       stock.setHighPrice(6);
       stock.setLowPrice(2);
       stock.setLastPrice(5.5);
        return stock;
    }

    private Holdings createHoldings() {
        // Create and return a valid Holdings for testing
        // Adjust the details based on your requirements
        Holdings holdings=new Holdings();
        holdings.setBuyPrice(3);
        holdings.setquantity(5);
        holdings.setHoldingId(1L);
        holdings.setstockId("PQ123");
        holdings.setuserId(6L);
        return holdings;
    }

    private List<Trade> createBuyTrades() {
        // Create and return a list of BUY trades for testing
        // Adjust the details based on your requirements
       Trade trade1=new Trade();
       Trade trade2=new Trade();
       trade1.setTradeId(1L);
       trade1.setBuyPrice(3);
       trade1.setQuantity(3);
       trade1.settradeType("BUY");
       trade1.setstockId("PQ123");
       trade1.setuserId(6L);
        trade2.setTradeId(2L);
        trade2.setBuyPrice(3);
        trade2.setQuantity(2);
        trade2.settradeType("BUY");
        trade2.setstockId("PQ123");
        trade2.setuserId(6L);
        List<Trade> list_of_Trades=new ArrayList<Trade>();
        list_of_Trades.add(trade1);
        list_of_Trades.add(trade2);
        return list_of_Trades;
    }
    private List<Trade> createSellTrades() {
        // Create and return a list of BUY trades for testing
        // Adjust the details based on your requirements
        Trade trade1=new Trade();
        Trade trade2=new Trade();
        trade1.setTradeId(1L);
        trade1.setBuyPrice(0);
        trade1.setQuantity(3);
        trade1.settradeType("SELL");
        trade1.setstockId("PQ123");
        trade1.setuserId(6L);
        trade2.setTradeId(2L);
        trade2.setBuyPrice(0);
        trade2.setQuantity(2);
        trade2.settradeType("SELL");
        trade2.setstockId("PQ123");
        trade2.setuserId(6L);
        List<Trade> list_of_Trades=new ArrayList<Trade>();
        list_of_Trades.add(trade1);
        list_of_Trades.add(trade2);
        return list_of_Trades;
    }
}
