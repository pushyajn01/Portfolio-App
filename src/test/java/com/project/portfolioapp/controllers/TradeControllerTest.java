package com.project.portfolioapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.portfolioapp.dto.TradeDTO;
import com.project.portfolioapp.services.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }

    @Test
    public void testProcessTrade_Success() throws Exception {
        TradeDTO tradeDTO = createTradeDTO();

        doNothing().when(tradeService).processTrade(any(TradeDTO.class));

        performPostRequest(tradeDTO)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testProcessTrade_InvalidParameters() throws Exception {
        TradeDTO tradeDTO = createInvalidTradeDTO();

        doThrow(new IllegalArgumentException("Invalid trade parameters"))
                .when(tradeService).processTrade(any(TradeDTO.class));

        performPostRequest(tradeDTO)
                .andExpect(status().isBadRequest());
    }

    private ResultActions performPostRequest(TradeDTO tradeDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(tradeDTO);

        return mockMvc.perform(post("/api/v1/trades/process-trade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
    }

    private TradeDTO createTradeDTO() {
        // Create and return a valid TradeDTO for testing
        // Adjust the details based on your requirements
TradeDTO tradeDTO=new TradeDTO();
tradeDTO.setTradeType("BUY");
tradeDTO.setQuantity(2);
tradeDTO.setStockId("PQR345");
tradeDTO.setUserId(5L);
        return tradeDTO;
    }

    private TradeDTO createInvalidTradeDTO() {
        // Create and return an invalid TradeDTO for testing
        // Adjust the details based on your requirements
        TradeDTO tradeDTO=new TradeDTO();
        tradeDTO.setTradeType("SELL");
        tradeDTO.setQuantity(2);
        tradeDTO.setStockId("TUV234");
        tradeDTO.setUserId(10L);
        return tradeDTO;
    }
}
