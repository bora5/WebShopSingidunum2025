package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Korisnik;
import repo.KorisnikRepo;

public class KorisnikService implements HttpHandler {

	private KorisnikRepo repo;

	public KorisnikService() {
		repo = new KorisnikRepo();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		String path = exchange.getRequestURI().getPath();
		Long id = extractIdFromPath(path);

		try {
			switch(exchange.getRequestMethod()) {
			case "GET":
				if (id == null) {
					List<Korisnik> list = repo.findAll();
					sendResponse(exchange, 200, toJSONArray(list));
				} else {
					Korisnik k = repo.findById(id);
					if (k != null) {
						sendResponse(exchange, 200, toJSON(k));
					} else {
						sendResponse(exchange, 404, "{\"error\":\"Korisnik nije pronađen.\"}");
					}
				}
				break;
			case "POST":
				String jsonRequest = readStringFromStream(exchange.getRequestBody());
				Korisnik novi = fromJSON(jsonRequest);

				if (novi != null) {
					Korisnik kreiran = repo.create(novi);
					if (kreiran != null) {
						sendResponse(exchange, 201, toJSON(kreiran));
					} else {
						sendResponse(exchange, 500, "{\"error\":\"Neuspešno kreiranje.\"}");
					}
				} else {
					sendResponse(exchange, 400, "{\"error\":\"Loš JSON format.\"}");
				}
				break;
			case "PUT":
				if (id == null) {
					sendResponse(exchange, 400, "{\"error\":\"ID je obavezan za izmenu.\"}");
					return;
				}

				String jsonUpdate = readStringFromStream(exchange.getRequestBody());
				Korisnik zaIzmenu = fromJSON(jsonUpdate);

				if (zaIzmenu != null) {
					zaIzmenu.setId(id); 
					Korisnik izmenjen = repo.update(zaIzmenu);
					if (izmenjen != null) {
						sendResponse(exchange, 200, toJSON(izmenjen));
					} else {
						sendResponse(exchange, 404, "{\"error\":\"Korisnik nije pronađen.\"}");
					}
				} else {
					sendResponse(exchange, 400, "{\"error\":\"Loš JSON format.\"}");
				}
				break;
			case "DELETE":
				if (id == null) {
					sendResponse(exchange, 400, "{\"error\":\"ID je obavezan za brisanje.\"}");
				} else {
					Boolean res = repo.delete(id);
					if (res == null)
						sendResponse(exchange, 500, "{\"error\":\"Neuspešno brisanje.\"}");
					else if (res)
						sendResponse(exchange, 204, "");
					else
						sendResponse(exchange, 400, "{\"error\":\"ID za brisanje ne postoji.\"}");
				}
				break;
			default:
				sendResponse(exchange, 405, "{\"error\":\"Metod nije dozvoljen.\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendResponse(exchange, 500, "{\"error\":\"Doslo je do greske na serveru\"}");
		}
	}

	//https://www.example.org/user/posts/1
	private Long extractIdFromPath(String path) {
		String[] parts = path.split("/");
		if (parts.length > 0) {
			try {
				return Long.parseLong(parts[parts.length - 1]);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
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

	private String toJSON(Korisnik k) {
		return """
				{
				"id":%d,
				"ime":"%s",
				"prezime":"%s",
				"korisnickoIme":"%s"
				}
				""".formatted(k.getId(), k.getIme(), k.getPrezime(), k.getKorisnickoIme());
	}

	private String toJSONArray(List<Korisnik> list) {
		StringBuilder sb = new StringBuilder();

		sb.append("[").append("\n");

		Iterator<Korisnik> iter = list.iterator();

		while(iter.hasNext()) {
			Korisnik k = iter.next();
			sb.append(toJSON(k));
			if (iter.hasNext()) 
				sb.append(",\n");
			else
				sb.append("\n");
		}
		sb.append("]");

		return sb.toString();		
	}

	private Korisnik fromJSON(String s) {
		try {
			String content = s.replace("{", "").replace("}", "").replace("\"", "");
			String[] pairs = content.split(",");

			Long id = null;
			String ime = null;
			String prezime = null;
			String korisnickoIme = null;
			String lozinka = null;

			for (String pair: pairs) {
				String[] keyValue = pair.split(":");
				if (keyValue.length < 2) continue;
				
				String key = keyValue[0].trim();
				String value = keyValue[1].trim();

				if("id".equals(key)) {
					try {
						id = Long.parseLong(value);
					} catch (Exception e) {
					}
				}
				else if("ime".equals(key))
					ime = value;
				else if("prezime".equals(key))
					prezime = value;
				else if("korisnickoIme".equals(key))
					korisnickoIme = value;
				else if("lozinka".equals(key))
					lozinka = value;
			}
			return new Korisnik(id, ime, prezime, korisnickoIme, lozinka);
		} catch (Exception e) {
			return null;
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

}
