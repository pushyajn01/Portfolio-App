package com.project.portfolioapp.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void updateStocks(InputStream fileInputStream)
    {
        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(fileInputStream)).build()) {
            csvReader.skip(1);
            List<String[]> rows = csvReader.readAll();
            List<Stock> stockList = new ArrayList<>();

            for (String[] row : rows) {

                String stockId = row[12];
                String stockName = row[0];
                double openPrice = Double.parseDouble(row[2]);
                double closePrice = Double.parseDouble(row[5]);
                double lowPrice = Double.parseDouble(row[4]);
                double highPrice = Double.parseDouble(row[3]);
                double lastPrice= Double.parseDouble(row[6]);

                Stock stock = new Stock(stockId, stockName, openPrice, closePrice, lowPrice, highPrice,lastPrice);
                stockList.add(stock);
            }


            stockRepository.saveAll(stockList);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Stock getStockById(String stockId) {
        return stockRepository.findByStockId(stockId);
    }


}
