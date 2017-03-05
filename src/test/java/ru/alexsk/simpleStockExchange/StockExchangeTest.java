package ru.alexsk.simpleStockExchange;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockExchangeTest {

	private static String clientsFile = "src/test/resources/clients.txt";
	private static String transactionsFile = "src/test/resources/orders.txt";
	private static String resultsFile = "src/test/resources/result.txt";
	
	static StockExchange instance;
	static HashMap<String, Client> clients;
	
	@BeforeClass
	public static void init() { 
		instance = new StockExchange();
	}
	
	@Test
	public void initClientsMapTest () {
		instance.initClientsMap(StockExchangeTest.clientsFile);
		clients = instance.getClients();
		assertEquals("Check assount sum:", clients.get("C1").getAccount(), new BigDecimal("1000"));
		assertEquals("Check assount sum:", clients.get("C2").getAccount(), new BigDecimal("2000"));
	}
	
	@Test
	public void processOrdersTest () {
		instance.processOrders(StockExchangeTest.transactionsFile);
		assertEquals("Check assount sum:", clients.get("C1").getAccount(), new BigDecimal("980"));
		assertEquals("Check assount sum:", clients.get("C2").getAccount(), new BigDecimal("2100"));
	}
	
	
	
	@AfterClass 
	public static void packResultsTest () {
		instance.packResults (StockExchangeTest.resultsFile);
	}


}
