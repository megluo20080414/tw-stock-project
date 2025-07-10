package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StockPriceFetcher {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String fetchLatestPrice(String stockNo) throws IOException, InterruptedException {
        String date = "20250601"; // 可改為當月
        String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=" + date + "&stockNo="
                + stockNo;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode json = mapper.readTree(response.body());
        if (!"OK".equals(json.get("stat").asText())) {
            throw new IllegalStateException("API 回傳失敗");
        }

        JsonNode data = json.get("data");
        if (data.isArray() && data.size() > 0) {
            JsonNode latest = data.get(0);
            return latest.get(6).asText(); // 收盤價
        } else {
            throw new IllegalStateException("找不到股價資料");
        }
    }
}
