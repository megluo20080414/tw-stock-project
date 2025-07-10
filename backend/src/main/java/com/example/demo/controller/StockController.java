package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @GetMapping("/{stockNo}")
    public ResponseEntity<?> getStockPrice(@PathVariable String stockNo) {
        String date = "20240701"; // 寫死也可以先測試
        String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date="
                + date + "&stockNo=" + stockNo;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok(response);
    }
}
