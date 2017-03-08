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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FileParser implements Parser{

	private static String clientsFile = "clients.txt";
	private static String ordersFile = "orders.txt";
	private static String resultsFile = "result.txt";
	
	private  HashMap<String, Client> clients = new HashMap<String, Client>();
	private  ArrayList<Order> orders = new ArrayList<Order>();
	
	public Iterator<Client> readClients() {
		
		Path path = Paths.get(clientsFile);
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
			System.out.println("File client.txt searach/open problems");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Illigal client.txt file format");
			System.exit(0);
		}
		return clients.values().iterator();
	}

	public Iterator<Order> readOrders() {
		Path path = Paths.get(ordersFile);
		BufferedReader reader = null;
		String line = null;
		
		try  {
			
			reader = Files.newBufferedReader(path);
			while ((line = reader.readLine()) != null) {
				
				String [] orderParams = line.split("\t");
				
				Order order = new Order();
				
				order.client = clients.get(orderParams[0]);
				order.operation = orderParams[1].charAt(0);
				order.stockName = orderParams[2];
				order.stockQuantity = Integer.parseInt(orderParams[3]);
				order.stockPrice = new BigDecimal(orderParams[4]);
				
				orders.add(order);
				
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File order.txt searach/open problems");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Illigal order.txt file format");
			System.exit(0);
		}
		

		return orders.iterator();
	}

	public boolean writeClients() {
		
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
			System.out.println("Can't write result.txt file");
			System.exit(0);
		}
		
		return false;
	}

}
