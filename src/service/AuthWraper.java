package service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import utils.JwtUtil;

public class AuthWraper implements HttpHandler {
	
	private final HttpHandler next;
	
	public AuthWraper(HttpHandler next) {
		this.next = next;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String authHeader = exchange.getRequestHeaders().getFirst("Authorization");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			
			if (JwtUtil.validateToken(token))
				next.handle(exchange);
			return;
		}
		
		sendResponse(exchange, 401, "{\"error\":\"Pristup odbijen. Potreban validan token.\"}");
		
	}
	
	private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
		byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
		exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
		exchange.sendResponseHeaders(statusCode, (bytes.length == 0 ? -1 : bytes.length));
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(bytes);
		}
	}

}
