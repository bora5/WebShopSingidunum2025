package main;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import service.AuthWraper;
import service.KorisnikService;
import service.LoginService;

public class WebShop {

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
			
			server.createContext("/login", new LoginService());
			server.createContext("/korisnici", new AuthWraper(new KorisnikService()));
			
			server.setExecutor(null);
			System.out.println("Server is running on port 8080!!!");
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}