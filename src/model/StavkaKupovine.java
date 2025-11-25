package model;

public class StavkaKupovine extends IdentifiableClass {
	
	private Long kupovinaId;
	private Long artikalId;
	private int kolicina;
	private double jedicnaCena;
	
	public StavkaKupovine(Long id, Long kupovinaId, Long artikalId, int kolicina, double jedicnaCena) {
		super(id);
		this.kupovinaId = kupovinaId;
		this.artikalId = artikalId;
		this.kolicina = kolicina;
		this.jedicnaCena = jedicnaCena;
	}

	public Long getKupovinaId() {
		return kupovinaId;
	}

	public void setKupovinaId(Long kupovinaId) {
		this.kupovinaId = kupovinaId;
	}

	public Long getArtikalId() {
		return artikalId;
	}

	public void setArtikalId(Long artikalId) {
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
