package ru.alexsk.simpleStockExchange;
import java.math.BigDecimal;
import java.util.HashMap;

public class Client {
	
	private String name;
	
	private BigDecimal account;
	
	private HashMap<String, Integer> stocks;
	
	private Client() {}
	
	public Client (String name, BigDecimal account, HashMap<String, Integer> stocks) {
		this.account = account;
		this.name = name;
		this.stocks = stocks;
	}
	
	public boolean sellStocks (String stockName, int qantity, BigDecimal price) {
		BigDecimal tprice  = BigDecimal.ZERO;
		tprice  = price.multiply(new BigDecimal(qantity));
		account = account.add(tprice);
		this.stocks.put(stockName, this.stocks.get(stockName) - qantity);
		return true;
	}
	
	public boolean buyStocks (String stockName, int qantity, BigDecimal price) {
		BigDecimal tprice  = BigDecimal.ZERO;
		tprice  = price.multiply(new BigDecimal(qantity));
		account = account.subtract(tprice);
		this.stocks.put(stockName, this.stocks.get(stockName) + qantity);
		return true;
	}

	public String getName () {
		return this.name;
	}
	
	public BigDecimal getAccount () {
		return this.account;
	}
	
	public HashMap<String, Integer> getStocks () {
		return stocks;
	}
	
}
