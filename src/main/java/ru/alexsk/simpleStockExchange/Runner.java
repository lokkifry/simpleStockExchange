package ru.alexsk.simpleStockExchange;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StockExchange stockExchange = new StockExchange();
		stockExchange.process();
		stockExchange.writeResults();

	}

}
