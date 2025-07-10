package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @GetMapping("/{stockNo}")
    public ResponseEntity<?> getStockPrice(@PathVariable String stockNo) {
        String date = "20240701"; // 寫死測試
        String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date="
                + date + "&stockNo=" + stockNo;

        // 建立 headers 並加入 User-Agent
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        // 使用 exchange 傳送帶有 headers 的 GET 請求
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
