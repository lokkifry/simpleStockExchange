package ru.alexsk.simpleStockExchange;

public class Runner {
	
	public static void main(String[] args) {
		
		StockExchange stockExchange = new StockExchange();
		stockExchange.process();
		stockExchange.writeResults();

	}

}
