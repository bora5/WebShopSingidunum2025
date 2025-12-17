package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Korisnik;
import repo.KorisnikRepo;
import utils.JwtUtil;

public class LoginService implements HttpHandler {
	
	private KorisnikRepo repo;
	
	public LoginService() {
		repo = new KorisnikRepo();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if ("POST".equals(exchange.getRequestMethod())) {
			String body = readStringFromStream(exchange.getRequestBody());
			String username = extractString(body, "username");
			String password = extractString(body, "password");
			
			Korisnik k = repo.proveriKredencijale(username, password);
			if (k != null) {
				String token = JwtUtil.generateToken(k.getKorisnickoIme(), k.getId());
				String response = String.format("{\"token\":\"%s\"}", token);
				sendResponse(exchange, 200, response);
			} else {
				sendResponse(exchange, 401, "{\"error\":\"Pogrešni kredencijali.\"}");
			}
		} else {
			sendResponse(exchange, 405, "{\"error\":\"Metod nije dozvoljen.\"}");
		}
	}
	
	private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
		byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
		exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
		exchange.sendResponseHeaders(statusCode, (bytes.length == 0 ? -1 : bytes.length));
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(bytes);
		}
	}
	
	private String readStringFromStream(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	private String extractString(String payload, String key) {
		String term = "\"" + key + "\":";
		int start = payload.indexOf(term);
		if (start == -1) return null;
		start += term.length() + 1;
		int end = payload.indexOf("\"", start);
		if (end == -1) return null;
		return payload.substring(start, end).trim();
	}

}
