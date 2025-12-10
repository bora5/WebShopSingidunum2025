package main;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import service.KorisnikService;

public class WebShop {

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			
			server.createContext("/korisnici", new KorisnikService());
			
			server.setExecutor(null);
			System.out.println("Server is running on port 8080!!!");
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}