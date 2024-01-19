package com.project.portfolioapp.controllers;

import com.project.portfolioapp.dto.TradeDTO;
import com.project.portfolioapp.services.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/trades")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping("/process-trade")
    public ResponseEntity<String>processTrade(@RequestBody TradeDTO tradeDTO)
    {
        try {
            tradeService.processTrade(tradeDTO);
            return ResponseEntity.ok("Trade processed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
