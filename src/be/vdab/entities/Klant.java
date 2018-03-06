package be.vdab.entities;

import be.vdab.valueObjects.Adres;

public class Klant {
	private long id;
	private String voornaam;
	private String familienaam;
	private Adres adres;
	private String gebruikersnaam;
	private String paswoord;

	public Klant(long id, String voornaam, String familienaam, Adres adres, String gebruikersnaam, String paswoord) {
		this.id = id;
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.adres = adres;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}
	
	public Klant(String voornaam, String familienaam, Adres adres, String gebruikersnaam, String paswoord) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.adres = adres;
		this.gebruikersnaam = gebruikersnaam;
		this.paswoord = paswoord;
	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public Adres getAdres() {
		return adres;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gebruikersnaam == null) ? 0 : gebruikersnaam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Klant))
			return false;
		Klant other = (Klant) obj;
		if (gebruikersnaam == null) {
			if (other.gebruikersnaam != null)
				return false;
		} else if (!gebruikersnaam.equals(other.gebruikersnaam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return voornaam + " " + familienaam + " " + adres.toString();
	}
}
