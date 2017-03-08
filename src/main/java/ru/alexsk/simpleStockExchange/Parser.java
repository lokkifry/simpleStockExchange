package ru.alexsk.simpleStockExchange;

import java.util.Iterator;

public interface Parser {

	Iterator<Client> readClients ();
	
	Iterator<Order> readOrders ();
	
	boolean writeClients ();
	
}
