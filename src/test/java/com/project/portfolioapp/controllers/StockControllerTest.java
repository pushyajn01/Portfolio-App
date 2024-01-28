package com.project.portfolioapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.portfolioapp.controllers.StockController;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.services.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//@WebMvcTest(StockController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Used for converting objects to JSON

    @MockBean
    private StockService stockService; // Mock the StockService

    @Test
    public void testGetStockById() throws Exception {
        String stockId = "ABC123";
        Stock stock = new Stock();
        stock.setStockId(stockId);
        // Mock the service response
        when(stockService.getStockById(stockId)).thenReturn(stock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stocks/getstock/{stockId}", stockId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.stockId").value(stockId));
    }

    @Test
    public void testGetStockByIdNotFound() throws Exception {
        String stockId = "XYZ789";
        // Mock the service response for not found
        when(stockService.getStockById(stockId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/stocks/getstock/{stockId}", stockId))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testupdateStocks() throws Exception {
        String filePath = "/Users/pushyaj/downloads/cm15JAN2024bhav.csv";
        InputStream inputStream = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", "cm15JAN2024bhav.csv", "text/csv", inputStream);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/stocks/update-stocks")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Stocks updated successfully"));

    }
}
