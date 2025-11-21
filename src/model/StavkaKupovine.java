package model;

public class StavkaKupovine {
	
	private Long id;
	private Kupovina kupovinaId;
	private Artikal artikalId;
	private int kolicina;
	private double jedicnaCena;
	
	public StavkaKupovine(Long id, Kupovina kupovinaId, Artikal artikalId, int kolicina, double jedicnaCena) {
		super();
		this.id = id;
		this.kupovinaId = kupovinaId;
		this.artikalId = artikalId;
		this.kolicina = kolicina;
		this.jedicnaCena = jedicnaCena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Kupovina getKupovinaId() {
		return kupovinaId;
	}

	public void setKupovinaId(Kupovina kupovinaId) {
		this.kupovinaId = kupovinaId;
	}

	public Artikal getArtikalId() {
		return artikalId;
	}

	public void setArtikalId(Artikal artikalId) {
		this.artikalId = artikalId;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getJedicnaCena() {
		return jedicnaCena;
	}

	public void setJedicnaCena(double jedicnaCena) {
		this.jedicnaCena = jedicnaCena;
	}
	

}
