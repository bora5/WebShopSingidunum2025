package model;

import java.time.LocalDateTime;

public class Kupovina extends IdentifiableClass {
	
	private Long korisnikId;
	private LocalDateTime vremeKupovine;
	private StatusKupovine status;
	private double ukupnaCena;
	
	public Kupovina(Long id, Long korisnikId, LocalDateTime vremeKupovine, StatusKupovine status, double ukupnaCena) {
		super(id);
		this.korisnikId = korisnikId;
		this.vremeKupovine = vremeKupovine;
		this.status = status;
		this.ukupnaCena = ukupnaCena;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public LocalDateTime getVremeKupovine() {
		return vremeKupovine;
	}

	public void setVremeKupovine(LocalDateTime vremeKupovine) {
		this.vremeKupovine = vremeKupovine;
	}

	public StatusKupovine getStatus() {
		return status;
	}

	public void setStatus(StatusKupovine status) {
		this.status = status;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	
}
