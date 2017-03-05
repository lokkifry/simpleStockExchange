package ru.alexsk.simpleStockExchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockExchange {

	private static String clientsFile = "src/main/resources/clients.txt";
	private static String transactionsFile = "src/main/resources/orders.txt";
	private static String resultsFile = "src/main/resources/result.txt";

	private  HashMap<String, Client> clients = new HashMap<String, Client>();

	public static void main(String[] args) {

		StockExchange application = new StockExchange();
		
		application.initClientsMap(StockExchange.clientsFile);
		application.processOrders(StockExchange.transactionsFile);
		application.packResults(StockExchange.resultsFile);

		
	}
	
	public void initClientsMap (String cilentsFile) {
		
		Path path = Paths.get(cilentsFile);
		BufferedReader reader = null;
		String line = null;

		try  {
			reader = Files.newBufferedReader(path);
			while ((line = reader.readLine()) != null) {
				
		    	String [] clientParams = line.split("\t");
		        
		        HashMap<String, Integer> stocks = new HashMap<String, Integer>();
		        stocks.put("A", Integer.parseInt(clientParams[2]));
		        stocks.put("B", Integer.parseInt(clientParams[3]));
		        stocks.put("C", Integer.parseInt(clientParams[4]));
		        stocks.put("D", Integer.parseInt(clientParams[5]));
		        
		        Client client = new Client(clientParams[0], new BigDecimal(clientParams[1]), stocks);
		        
		        this.clients.put(clientParams[0], client);
		        
			}
		    reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void processOrders (String ordersFile) {
		
		Path path = Paths.get(ordersFile);
		BufferedReader reader = null;
		String line = null;
		
		try  {
			
			reader = Files.newBufferedReader(path);
			while ((line = reader.readLine()) != null) {
				String [] orderParams = line.split("\t");
				
				if (orderParams[1].equals("b")) {
					
					this.clients.get(orderParams[0]).buyStocks(
														orderParams[2], 
														Integer.parseInt(orderParams[3]), 
														new BigDecimal(orderParams[4]));
					
				}
				if (orderParams[1].equals("s")) {
				
					this.clients.get(orderParams[0]).sellStocks(
														orderParams[2], 
														Integer.parseInt(orderParams[3]), 
														new BigDecimal(orderParams[4]));
					
				}
			}
		    reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void packResults (String resultsFile) {
		
		PrintWriter pw = null;
		Map<String, Client> sortedClientMap = new TreeMap<String, Client>(this.clients);
		
		try {
			pw = new PrintWriter(new FileOutputStream(new File(resultsFile)));
			for (Client client: sortedClientMap.values()) {
				
				pw.printf("%s\t%s\t%d\t%d\t%d\t%d\n",
						client.getName(),
						client.getAccount().toString(),
						client.getStocks().get("A"),
						client.getStocks().get("B"),
						client.getStocks().get("C"),
						client.getStocks().get("D"));
			}
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	      
	}
	
	public HashMap<String, Client> getClients () {
		return this.clients;
	}
	

}
