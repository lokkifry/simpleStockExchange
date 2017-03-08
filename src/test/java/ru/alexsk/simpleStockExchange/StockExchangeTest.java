package ru.alexsk.simpleStockExchange;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StockExchangeTest {

	
	static StockExchange instance;
	
	@BeforeClass
	public static void init() { 
		instance = new StockExchange();
	}
	
	@Test
	public void processTest () {
		instance.process();
	}
	
	
	@AfterClass 
	public static void packResultsTest () {
		instance.writeResults ();
	}


}
