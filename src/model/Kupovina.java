package model;

import java.time.LocalDateTime;
import java.util.Set;

public class Kupovina {
	
	private Long id;
	private Korisnik korisnikId;
	private LocalDateTime vremeKupovine;
	private StatusKupovine status;
	private double ukupnaCena;
	private Set<StavkaKupovine> stavkaKupovines;
	
	public Kupovina(Long id, Korisnik korisnikId, LocalDateTime vremeKupovine, StatusKupovine status, double ukupnaCena) {
		super();
		this.id = id;
		this.korisnikId = korisnikId;
		this.vremeKupovine = vremeKupovine;
		this.status = status;
		this.ukupnaCena = ukupnaCena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Korisnik korisnikId) {
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

	public Set<StavkaKupovine> getStavkaKupovines() {
		return stavkaKupovines;
	}

	public void setStavkaKupovines(Set<StavkaKupovine> stavkaKupovines) {
		this.stavkaKupovines = stavkaKupovines;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	
}
