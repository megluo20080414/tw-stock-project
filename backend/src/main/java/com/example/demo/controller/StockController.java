package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @GetMapping("/{stockNo}")
    public ResponseEntity<?> getStockPrice(
            @PathVariable String stockNo,
            @RequestParam(required = false) String date) {

        // 若沒有傳 date，預設為當月的 1 號（yyyyMM01）
        if (date == null || date.isEmpty()) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
            date = today.format(formatter) + "01";
        }

        String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json"
                   + "&date=" + date
                   + "&stockNo=" + stockNo;

        // 設定 User-Agent header（避免被擋）
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
