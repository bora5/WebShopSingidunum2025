package model;

public class Korisnik extends IdentifiableClass {
	
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private String lozinka;
	
	public Korisnik(Long id, String ime, String prezime, String korisnickoIme, String lozinka) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
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
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "ID: %d, ime: %s, prezime: %s".formatted(getId(), ime, prezime);
	}

	
}
