package model;

public class Artikal extends IdentifiableClass {
	
	private String naziv;
	private double cena;
	
	public Artikal(Long id, String naziv, double cena) {
		super(id);
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

}
