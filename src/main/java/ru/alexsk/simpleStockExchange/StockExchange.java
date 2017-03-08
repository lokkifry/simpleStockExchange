package ru.alexsk.simpleStockExchange;

import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class StockExchange {

	Parser parser;
	Iterator<Order> orders;
	Iterator<Client> clients;
	private final Lock lock = new ReentrantLock();

	StockExchange () {
		this.parser = new FileParser();
		init();
	}
	
	private void init () {
		clients = parser.readClients();
		orders = parser.readOrders();
	}
	
	public int process () {
		
		int counter = 0;
		
		while (this.orders.hasNext()) {

			lock.lock();
			
			try {
				
				Order order = orders.next();
				if (this.processOrder(order)) this.orders.remove();
				else System.out.println("order prossesed error");
				counter++;	
				
			} finally {
				lock.unlock();
			}
			
		}
		
		return counter;
	}
	
	private boolean processOrder (Order order) {

		boolean result = false;
		
		if (order.operation == 'b') {
			order.client.buyStocks( order.stockName, order.stockQuantity,order.stockPrice);
			result = true;
		}
		if (order.operation == 's') {
			order.client.sellStocks( order.stockName, order.stockQuantity,order.stockPrice);
			result = true;
		}
		
		
		return result;
	}
	
	public void writeResults () {
		this.parser.writeClients();
	}
	
}
