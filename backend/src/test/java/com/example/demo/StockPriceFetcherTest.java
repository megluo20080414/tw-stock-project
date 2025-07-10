package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StockPriceFetcherTest {

    @Test
    void testFetchLatestPrice() throws Exception {
        StockPriceFetcher fetcher = new StockPriceFetcher();
        String price = fetcher.fetchLatestPrice("2881");
        System.out.println("富邦金收盤價: " + price);
        assertNotNull(price);
    }
}
