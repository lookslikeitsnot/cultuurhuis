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

	public Klant() {

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
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((familienaam == null) ? 0 : familienaam.hashCode());
		result = prime * result + ((gebruikersnaam == null) ? 0 : gebruikersnaam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((paswoord == null) ? 0 : paswoord.hashCode());
		result = prime * result + ((voornaam == null) ? 0 : voornaam.hashCode());
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
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (familienaam == null) {
			if (other.familienaam != null)
				return false;
		} else if (!familienaam.equals(other.familienaam))
			return false;
		if (gebruikersnaam == null) {
			if (other.gebruikersnaam != null)
				return false;
		} else if (!gebruikersnaam.equals(other.gebruikersnaam))
			return false;
		if (id != other.id)
			return false;
		if (paswoord == null) {
			if (other.paswoord != null)
				return false;
		} else if (!paswoord.equals(other.paswoord))
			return false;
		if (voornaam == null) {
			if (other.voornaam != null)
				return false;
		} else if (!voornaam.equals(other.voornaam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return voornaam + " " + familienaam + " " + adres.toString();
	}

//	public void setVoornaam(String voornaam) {
//		if (StringUtils.isStringValid(voornaam)) {
//			throw new IllegalArgumentException();
//		}
//		this.voornaam = voornaam;
//	}
//
//	public void setFamilienaam(String familienaam) {
//		if (StringUtils.isStringValid(familienaam)) {
//			throw new IllegalArgumentException();
//		}
//		this.familienaam = familienaam;
//	}
//
//	public void setAdres(Adres adres) {
//		this.adres = adres;
//	}
//
//	public void setGebruikersnaam(String gebruikersnaam) {
//		this.gebruikersnaam = gebruikersnaam;
//	}
//
//	public void setPaswoord(String paswoord) {
//		this.paswoord = paswoord;
//	}

}
