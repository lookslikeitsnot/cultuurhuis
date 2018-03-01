package be.vdab.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Voorstelling {
	private long id;
	private String titel;
	private String uitvoerders;
	private LocalDateTime datum;
	private long genreId;
	private BigDecimal prijs;
	private int vrijePlaatsen;
	private int gereserveerdePlaatsen;
	public Voorstelling(long id, String titel, String uitvoerders, LocalDateTime datum, long genreId, BigDecimal prijs,
			int vrijePlaatsen) {
		this.id = id;
		this.titel = titel;
		this.uitvoerders = uitvoerders;
		this.datum = datum;
		this.genreId = genreId;
		this.prijs = prijs;
		this.vrijePlaatsen = vrijePlaatsen;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getUitvoerders() {
		return uitvoerders;
	}
	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	public long getGenreId() {
		return genreId;
	}
	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}
	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + (int) (genreId ^ (genreId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result + ((uitvoerders == null) ? 0 : uitvoerders.hashCode());
		result = prime * result + vrijePlaatsen;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Voorstelling))
			return false;
		Voorstelling other = (Voorstelling) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (genreId != other.genreId)
			return false;
		if (id != other.id)
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (uitvoerders == null) {
			if (other.uitvoerders != null)
				return false;
		} else if (!uitvoerders.equals(other.uitvoerders))
			return false;
		if (vrijePlaatsen != other.vrijePlaatsen)
			return false;
		return true;
	}
	public int getGereserveerdePlaatsen() {
		return gereserveerdePlaatsen;
	}
	public void setGereserveerdePlaatsen(int gereserveerdePlaatsen) {
		this.gereserveerdePlaatsen = gereserveerdePlaatsen;
	}
	
}
