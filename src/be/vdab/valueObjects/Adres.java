package be.vdab.valueObjects;

import be.vdab.entities.Gemeente;

public class Adres {
	private String straat;
	private String huisnr;
	private Gemeente gemeente;
	public Adres(String straat, String huisnr, Gemeente gemeente) {
		this.straat = straat;
		this.huisnr = huisnr;
		this.gemeente = gemeente;
	}
	public String getStraat() {
		return straat;
	}
	public String getHuisnr() {
		return huisnr;
	}
	public Gemeente getGemeente() {
		return gemeente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gemeente == null) ? 0 : gemeente.hashCode());
		result = prime * result + ((huisnr == null) ? 0 : huisnr.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Adres))
			return false;
		Adres other = (Adres) obj;
		if (gemeente == null) {
			if (other.gemeente != null)
				return false;
		} else if (!gemeente.equals(other.gemeente))
			return false;
		if (huisnr == null) {
			if (other.huisnr != null)
				return false;
		} else if (!huisnr.equals(other.huisnr))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return straat + " " + huisnr + ", " + gemeente.toString();
	}
}
