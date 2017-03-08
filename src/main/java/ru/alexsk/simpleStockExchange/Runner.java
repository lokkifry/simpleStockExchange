package ru.alexsk.simpleStockExchange;

public class Runner {
	
	public static void main(String[] args) {
		
		StockExchange stockExchange = new StockExchange();
		int ordersCount = stockExchange.process();
		System.out.printf("Processed : %d orders", ordersCount);
		stockExchange.writeResults();

	}

}
