package com.project.portfolioapp.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.repository.StockRepository;
import com.project.portfolioapp.services.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testUpdateStocks() {
        // Create a mock InputStream
        InputStream mockInputStream = mock(InputStream.class);

        // Mock CSV content
        String csvContent = "StockName,OpenPrice,ClosePrice,HighPrice,LowPrice,LastPrice,stockId\n" +
                "ABC,100,105,110,95,102,XYZ123\n";

        // Mock CSVReader behavior
        try (MockedStatic<CSVReaderBuilder> csvReaderBuilderMockedStatic = Mockito.mockStatic(CSVReaderBuilder.class)) {
            CSVReader csvReader = mock(CSVReader.class);
            when(csvReader.readAll()).thenReturn(Collections.singletonList(csvContent.split("\n")));
            //csvReaderBuilderMockedStatic.when(() -> new CSVReaderBuilder(any(Reader.class))).thenReturn(csvReader);
            // Test the updateStocks method
            //when(csvReader.getLinesRead()).thenReturn(null);
            PowerMockito.whenNew(CSVReaderBuilder.class).withAnyArguments().thenReturn(new CSVReaderBuilder(null));

            stockService.updateStocks(mockInputStream);

            // Verify that saveAll was called with the expected Stock object
            verify(stockRepository, times(1)).saveAll(any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStockById() {
        String stockId = "XYZ123";
        Stock expectedStock = new Stock();
        expectedStock.setStockId(stockId);

        // Mock the repository behavior
        when(stockRepository.findByStockId(stockId)).thenReturn(expectedStock);

        // Test the getStockById method
        Stock actualStock = stockService.getStockById(stockId);

        // Verify that the result is as expected
        assertEquals(expectedStock, actualStock);
    }
}
