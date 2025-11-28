package model;

public class Korisnik extends IdentifiableClass {
	
	private String ime;
	private String prezime;
	
	public Korisnik(Long id, String ime, String prezime) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	@Override
	public String toString() {
		return "ID: %d, ime: %s, prezime: %s".formatted(getId(), ime, prezime);
	}

	
}
