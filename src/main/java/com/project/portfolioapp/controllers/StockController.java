package com.project.portfolioapp.controllers;

import com.project.portfolioapp.dto.StockDTO;
import com.project.portfolioapp.model.Stock;
import com.project.portfolioapp.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    @Autowired
    private StockService stockService;
    @PostMapping("/update-stocks")
    public ResponseEntity<String>updateStocks(@RequestParam("file")MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is null or empty");
        }
        try (InputStream inputStream = file.getInputStream()) {
            if (inputStream == null) {
                throw new IOException("Input stream is null");

            }
            stockService.updateStocks(file.getInputStream());
            return ResponseEntity.ok("Stocks updated successfully");
        }
        catch (IOException e) {
            // Handle other IOExceptions if needed
            throw new IOException("Failed to process the file", e);
        }
    }

    @GetMapping(value = "/getstock/{stockId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock>getStockById(@PathVariable("stockId") String stockId)
    {
        Stock stock = stockService.getStockById(stockId);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
