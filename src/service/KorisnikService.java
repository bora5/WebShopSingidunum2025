package service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Korisnik;
import repo.KorisnikRepo;

public class KorisnikService implements HttpHandler {

	private KorisnikRepo repo = new KorisnikRepo();

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		switch(exchange.getRequestMethod()) {
		case "GET":

			break;
		case "POST":

			break;
		case "PUT":

			break;
		case "DELETE":

			break;
		}

	}

	private String toJSON(Korisnik k) {
		return """
				{
				"id":%d,
				"ime":"%s",
				"prezime":"%s"
				}
				""".formatted(k.getId(), k.getIme(), k.getPrezime());
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

			for (String pair: pairs) {
				String[] keyValue = pair.split(":");
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
			}
			return new Korisnik(id, ime, prezime);
		} catch (Exception e) {
			return null;
		}
	}

}
