package ru.alexsk.simpleStockExchange;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	
	private Client client;
	
    @Before
    public void init() { 
    	HashMap<String, Integer> stocks = new HashMap<String, Integer>();
    	stocks.put("A", 10);
    	stocks.put("B", 10);
    	client = new Client("test", new BigDecimal("100"), stocks);
    }

	
	@Test
	public void sellStocksTest () {
		this.client.sellStocks("A", 5, new BigDecimal("2"));
		assertEquals("Sell stocks test", client.getAccount(), new BigDecimal("110"));
	}
	
	@Test
	public void buyStocksTest () {
		this.client.buyStocks("B", 5, new BigDecimal("2"));
		assertEquals("Buy stocks test", client.getAccount(), new BigDecimal("90"));
	}

}
